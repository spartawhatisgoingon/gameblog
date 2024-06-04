package sparta.gameblog.mapper;

import org.springframework.stereotype.Component;
import sparta.gameblog.dto.UserSignupRequestDto;
import sparta.gameblog.entity.User;

@Component
public class UserMapper {
    public User toEntity(UserSignupRequestDto requestDto) {
        return User.builder()
                .name(requestDto.getName())
                .email(requestDto.getEmail())
                .password(requestDto.getPassword())
                .statusCode(User.StatusCode.INACTIVE)
                .build();
    }
}
