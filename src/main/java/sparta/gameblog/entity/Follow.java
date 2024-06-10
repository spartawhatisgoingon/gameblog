package sparta.gameblog.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Follow extends Timestamp{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "following_user_id")
    private User followingUser;

    @ManyToOne
    @JoinColumn(name = "follower_user_id")
    private User followerUser;

    @Builder
    public Follow(User followingUser, User followerUser) {
        this.followingUser = followingUser;
        this.followerUser = followerUser;
    }
}
