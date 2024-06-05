package sparta.gameblog.dto;

import lombok.Getter;
import sparta.gameblog.entity.User;

@Getter
public class ProfileResponseDto {
    private String email;
    private String name;
    private String introduction;

    public ProfileResponseDto(User user) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.introduction = user.getIntroduction();
    }
}
