package sparta.gameblog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NaverTokenResponseDto {
    private String access_token;
    private String refresh_token;
    private String token_type;
    private int expires_in;
    private String error;
    private String error_description;
}
