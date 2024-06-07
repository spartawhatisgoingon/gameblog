package sparta.gameblog.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import sparta.gameblog.dto.UserSignupRequestDto;
import sparta.gameblog.entity.User;

@Component
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User toEntity(UserSignupRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .statusCode(User.StatusCode.INACTIVE)
                .role(User.Role.NORMAL)
                .build();
    }
}
