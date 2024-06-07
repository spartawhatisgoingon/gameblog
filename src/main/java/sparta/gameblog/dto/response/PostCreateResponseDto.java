package sparta.gameblog.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sparta.gameblog.entity.Post;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
public class PostCreateResponseDto {
    private Long id;
    private String title;
    private String contents;
    private long userId;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    // 유저 정보는 포함하지 않았습니다.
    public PostCreateResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUser().getId();
        this.userEmail = post.getUser().getEmail();
    }
}
