package sparta.gameblog.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class LoginTokenDto {
    private String accessToken;
    private UUID refreshToken;
}
