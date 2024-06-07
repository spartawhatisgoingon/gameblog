package sparta.gameblog.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.LoginTokenDto;
import sparta.gameblog.dto.UserLoginDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.jwt.JwtUtil;
import sparta.gameblog.security.principal.UserPrincipal;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginTokenDto login(@Valid UserLoginDto requestDto) {
        Authentication authentication = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        requestDto.getEmail(), // principal
                        requestDto.getPassword(), // credential
                        null // authorities
                )
        );

        UserPrincipal userPrincipal = (UserPrincipal)authentication.getPrincipal();
        User user = userPrincipal.getUser();


        return LoginTokenDto.builder()
                .accessToken(jwtUtil.createAccessToken(user))
                .build();
    }
}
