package sparta.gameblog.dto;

import lombok.Getter;
import sparta.gameblog.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostGetResponseDto {
    private Long id;
    private String title;
    private String contents;
    private Long userId;
    private String user_email;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public PostGetResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();

        // 현재 User는 null이기 때문에 nullpointexception 오류가 발생합니다.
        // this.userId = post.getUser().getId();
        // this.user_email = post.getUser().getEmail();

        this.created_at = post.getCreatedAt();
        this.updated_at = post.getUpdatedAt();
    }
}
