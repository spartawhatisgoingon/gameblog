package sparta.gameblog.dto.response;

import lombok.Getter;
import sparta.gameblog.entity.User;

@Getter
public class ProfileResponseDto {
    private final String email;
    private final String name;
    private final String introduction;

    public ProfileResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.introduction = user.getIntroduction();
    }
}
