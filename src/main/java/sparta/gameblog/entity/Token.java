package sparta.gameblog.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private long id;

    private UUID token; // uuid 형식으로 넣기

    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Token(Type type, User user, long tokenExpirationSeconds ) {
        this.expiredAt = LocalDateTime.now().plusSeconds(tokenExpirationSeconds);
        this.token = UUID.randomUUID();
        this.type = type;
        this.user = user;
    }

    public enum Type {
        REFRESH_TOKEN,
        EMAIL_VALIDATION_TOKEN
    }


    // domain logic
    @Transient
    public boolean expired() {
        return this.expiredAt.isBefore(LocalDateTime.now());
    }
}
