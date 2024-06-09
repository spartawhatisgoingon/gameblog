package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sparta.gameblog.entity.Token;
import sparta.gameblog.entity.User;
import sparta.gameblog.repository.TokenRepository;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    @Value("${auth-code-expiration-seconds}")
    private int authCodeExpirationSeconds;

    public Token createEmailValidationToken(User user) {
        Token token = Token.builder()
                .type(Token.Type.EMAIL_VALIDATION_TOKEN)
                .user(user)
                .emailTokenExpirationSeconds(authCodeExpirationSeconds)
                .build();
        return this.tokenRepository.save(token);
    }
}
