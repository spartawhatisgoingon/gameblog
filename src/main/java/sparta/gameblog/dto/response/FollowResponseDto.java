package sparta.gameblog.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import sparta.gameblog.entity.Follow;

@Getter
@Setter
public class FollowResponseDto {
    @JsonProperty("following_user_id")
    private Long followingUserId;

    @JsonProperty("follower_user_id")
    private Long followerUserId;

    public FollowResponseDto(Follow follow) {
        this.followingUserId = follow.getFollowingUser().getId();
        this.followerUserId = follow.getFollowerUser().getId();
    }
}
