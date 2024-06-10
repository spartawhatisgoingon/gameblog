package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.Follow;
import sparta.gameblog.entity.User;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingUserIdAndFollowerUserId(long followingId, long followerId);

    List<Follow> findAllByFollowingUserId(long id);

    List<Follow> findAllByFollowerUserId(long id);
}
