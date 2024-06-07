package sparta.gameblog.security.filter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import sparta.gameblog.entity.User;
import sparta.gameblog.jwt.JwtUtil;
import sparta.gameblog.security.principal.UserPrincipal;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 매 요청이 올 때 마다, request 에서 header 의 Authorization 를 보고 jwt 토큰을 가져온 다음 가져온 값으로 세팅해야함
        /*
        1. header 에서 jwt token 가져오기
        2. jwt token 이 없으면 doFilter 호출
        3. 있으면 그걸로 유저 정보 세팅
         */

        String accessToken = jwtUtil.getJwtFromHeader(request);

        // token 이 없으면 생략하고 진행
        if (accessToken == null) {
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = jwtUtil.getClaimsFromAccessToken(accessToken);
        String email = claims.get(JwtUtil.CLAIM_EMAIL, String.class);
        String name = claims.get(JwtUtil.CLAIM_NAME, String.class);
        User.Role role = User.Role.valueOf(claims.get(JwtUtil.CLAIM_ROLE, String.class));

        UserDetails userDetails = new UserPrincipal(User.builder()
                .email(email)
                .name(name)
                .role(role)
                .build()
            );

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        SecurityContext context =  SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
