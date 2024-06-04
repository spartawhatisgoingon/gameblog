package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
