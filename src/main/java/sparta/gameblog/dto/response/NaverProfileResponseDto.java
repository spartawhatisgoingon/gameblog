package sparta.gameblog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NaverProfileResponseDto {
    private String resultcode;
    private String message;

    private NaverUserDetail response;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NaverUserDetail {
        private String id;
        private String nickname;
        private String email;
    }
}
