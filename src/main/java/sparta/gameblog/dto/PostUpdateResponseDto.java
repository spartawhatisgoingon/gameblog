package sparta.gameblog.dto;


import lombok.Getter;
import sparta.gameblog.entity.Post;

import java.time.LocalDateTime;

@Getter
public class PostUpdateResponseDto {

    private Long id;
    private String title;
    private String contents;
    private Long userId;
    private String userEmail;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public PostUpdateResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.userId = post.getUser().getId();
        this.userEmail = post.getUser().getEmail();
        this.createdAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }
}
