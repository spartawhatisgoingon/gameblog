package sparta.gameblog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginTokenDto {
    private String accessToken;
}
