package sparta.gameblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.gameblog.entity.Token;

import java.util.Optional;
import java.util.UUID;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Optional<Token> findByToken(UUID token);
}
