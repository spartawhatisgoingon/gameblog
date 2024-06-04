package sparta.gameblog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import sparta.gameblog.dto.PostCreateRequestDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private long id;
    private String title;
    private String contents;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Post(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
