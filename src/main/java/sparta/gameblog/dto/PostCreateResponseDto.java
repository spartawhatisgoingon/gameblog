package sparta.gameblog.dto;

import lombok.Getter;
import lombok.Setter;
import sparta.gameblog.entity.Post;
import java.time.LocalDateTime;

@Setter
@Getter
public class PostCreateResponseDto {
    private Long id;
    private String title;
    private String contents;
    private int user_id;
    private String user_email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    // 유저 정보는 포함하지 않았습니다.
    public PostCreateResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.created_at = post.getCreatedAt();
        this.updated_at = post.getUpdatedAt();
    }
}