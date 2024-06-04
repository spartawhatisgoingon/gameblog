package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sparta.gameblog.entity.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
