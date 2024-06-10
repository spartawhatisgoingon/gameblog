package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.Follow;

import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {
    Optional<Follow> findByFollowingUserIdAndFollowerUserId(long followingId, long followerId);
}
