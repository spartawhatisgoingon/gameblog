package sparta.gameblog.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import sparta.gameblog.entity.User;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Component
public class JwtUtil {
    public static final String CLAIM_ID = "id";
    public static final String CLAIM_ROLE = "role";
    public static final String CLAIM_EMAIL = "email";
    public static final String CLAIM_NAME = "name";
    public static final String CLAIM_STATUS_CODE = "status_code";
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String AUTHORIZATION_TYPE = "Bearer";

    @Value("${jwt.access-expire-time}")
    private long accessExpirationTime;

    @Getter
    @Value("${jwt.refresh-expire-time}")
    private long refreshExpirationTime;

    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    @Value("${jwt.key}")
    private String jwtKey;

    private Key key;

    static public boolean isExpiredAccessToken(HttpServletRequest request) {
        return request.getAttribute("expired") != null && (Boolean) request.getAttribute("expired");
    }

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(jwtKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    public String createAccessToken(User user) {
        Date date = new Date();
        Date expireDate = new Date(date.getTime() + accessExpirationTime);

        return Jwts.builder()
                // set 으로 하는건 정해진것
                .setSubject(user.getEmail())
                .setExpiration(expireDate)
                .setIssuedAt(date)
                // claim 우리가 넣고 싶은 정보들
                .claim(CLAIM_ID, user.getId())
                .claim(CLAIM_EMAIL, user.getEmail())
                .claim(CLAIM_ROLE, user.getRole())
                .claim(CLAIM_NAME, user.getName())
                .claim(CLAIM_STATUS_CODE, user.getStatusCode())
                .signWith(key, signatureAlgorithm)
                .compact();
    }

    public UUID createRefreshToken() {
//        Date date = new Date();
//        Date expireDate = new Date(date.getTime() + refreshExpirationTime);
        return UUID.randomUUID();
    }


    public String getJwtFromHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_TYPE)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public Claims getClaimsFromAccessToken(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    public boolean validateToken(String accessToken, HttpServletRequest request) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            log.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            request.setAttribute("expired", true);
            log.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            log.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            log.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }
}
