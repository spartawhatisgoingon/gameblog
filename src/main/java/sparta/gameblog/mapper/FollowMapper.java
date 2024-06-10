package sparta.gameblog.mapper;

import org.springframework.stereotype.Component;
import sparta.gameblog.entity.Follow;
import sparta.gameblog.entity.User;

@Component
public class FollowMapper {

    public Follow toEntity(User followingUser, User currentUSer) {
        return Follow.builder()
                .followingUser(followingUser)
                .followerUser(currentUSer)
                .build();
    }
}
