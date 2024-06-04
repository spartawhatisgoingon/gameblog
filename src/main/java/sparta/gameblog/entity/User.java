package sparta.gameblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import sparta.gameblog.constant.StatusCode;
import sparta.gameblog.constant.UserRole;

@Entity
@Getter
public class User extends Timestamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private long id;

    @Column(nullable = false)
    private String password;

    private String name;

    @Email
    @Column(unique = true, nullable = false)
    private String email;

    private String introduction;

    @Column(nullable = false)
    @Enumerated
    private StatusCode statusCode;

    @Column(nullable = false)
    @Enumerated
    private UserRole role;

}
