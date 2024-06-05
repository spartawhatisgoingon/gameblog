package sparta.gameblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import sparta.gameblog.constant.StatusCode;
import sparta.gameblog.constant.UserRole;
import lombok.AccessLevel;
import lombok.Builder;


@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    private Role role; // 0: normal, 1: admin

    @Builder
    public User(String password, String name, String email, StatusCode statusCode) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.statusCode = statusCode;
        this.role = Role.NORMAL;
    }

    public enum StatusCode {
        // 이메일 미인증
        INACTIVE,

        // 이메일 인증 완료
        ACTIVE,

        // 회원 삭제
        DELETED
    }

    public enum Role {
        NORMAL,
        ADMIN
    }

    // domain logic
    @Transient
    public void verify() {
        this.statusCode = StatusCode.ACTIVE;
    }

}
