package sparta.gameblog.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Token {
    @Id
    private String token;

    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public enum Type {
        REFRESH_TOKEN,
        EMAIL_VALIDATION_TOKEN
    }

}
