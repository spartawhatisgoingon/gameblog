package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
