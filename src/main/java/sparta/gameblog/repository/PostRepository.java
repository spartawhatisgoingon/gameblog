package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
}
