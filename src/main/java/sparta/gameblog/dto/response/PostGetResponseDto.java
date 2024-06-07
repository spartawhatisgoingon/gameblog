package sparta.gameblog.dto.response;

import lombok.Getter;
import sparta.gameblog.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostGetResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final Long userId;
    private final String userEmail;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public PostGetResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
        this.userId = post.getUser().getId();
        this.userEmail = post.getUser().getEmail();
    }
}
