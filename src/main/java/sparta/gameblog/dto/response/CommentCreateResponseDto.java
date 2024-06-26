package sparta.gameblog.dto.response;


import lombok.Getter;
import sparta.gameblog.entity.Comment;

import java.time.LocalDateTime;

@Getter
public class CommentCreateResponseDto {

    private Long id;
    private Long postId;
    private Long userId;
    private String comment;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public CommentCreateResponseDto(Comment comment) {
        this.id = comment.getId();
        this.postId = comment.getPost().getId();
        this.userId = comment.getUser().getId();
        this.comment = comment.getComment();
        this.createdAt = comment.getCreatedAt();
        this.updatedAt = comment.getUpdatedAt();

    }
}
