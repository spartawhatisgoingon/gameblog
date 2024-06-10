package sparta.gameblog.service;


import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sparta.gameblog.entity.Token;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.jwt.JwtUtil;
import sparta.gameblog.repository.TokenRepository;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;
    private final JwtUtil jwtUtil;

    @Value("${auth-code-expiration-seconds}")
    private int authCodeExpirationSeconds;

    public Token createEmailValidationToken(User user) {
        Token token = Token.builder()
                .type(Token.Type.EMAIL_VALIDATION_TOKEN)
                .user(user)
                .tokenExpirationSeconds(authCodeExpirationSeconds)
                .build();
        return this.tokenRepository.save(token);
    }

    public String reissueAccessToken(Token refreshToken) {
        if (refreshToken.expired()) {
            throw new BusinessException(ErrorCode.EXPIRED_TOKEN);
        }

        return jwtUtil.createAccessToken(refreshToken.getUser());
    }

    public void saveToken(Token token){
        tokenRepository.save(token);
    }

    public Token findByToken(UUID refreshToken) {
        return tokenRepository.findByToken(refreshToken).orElseThrow(
                () -> new BusinessException(ErrorCode.NOT_FOUND)
        );
    }
}
