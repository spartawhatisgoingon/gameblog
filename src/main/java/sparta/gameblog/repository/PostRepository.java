package sparta.gameblog.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.gameblog.entity.Post;

import java.time.LocalDateTime;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByTitleContainingAndCreatedAtBetween(
            String title,
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );
}
