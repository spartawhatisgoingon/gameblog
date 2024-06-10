package sparta.gameblog.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FollowRequestDto {
    @NotBlank
    @JsonProperty("following_user_id")
    private Long followingUserId;
}
