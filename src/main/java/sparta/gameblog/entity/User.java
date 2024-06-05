package sparta.gameblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@Getter
@Entity
@NoArgsConstructor
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

    /*
     * 이거의 장점? -> 위에다 만들면 NoArgsCons & AllArgsCons 를 다 생성해야함
     * 거기다가 id 는 repository 가 알아서 만들도록 위임해야하는데 AllArgs 를 사용하면 id 도 기본값으로 자동 세팅됨
     */
    @Builder
    public User(String password, String name, String email, StatusCode statusCode) {
        this.password = password;
        this.name = name;
        this.email = email;
        this.statusCode = statusCode;
        this.role = Role.NORMAL;
    }

    // domain logic
    @Transient
    public void verify() {
        this.statusCode = StatusCode.ACTIVE;
    }

    @Transactional
    public void updateProfile(String name, String email, String introduction, String password) {
        this.name = name;
        this.email = email;
        this.introduction = introduction;
        this.password = password;
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

}
