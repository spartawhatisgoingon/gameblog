package sparta.gameblog.mapper;

import org.springframework.stereotype.Component;
import sparta.gameblog.dto.CommentCreateRequestDto;
import sparta.gameblog.dto.CommentCreateResponseDto;
import sparta.gameblog.dto.PostCreateResponseDto;
import sparta.gameblog.entity.Comment;
import sparta.gameblog.entity.Post;
import sparta.gameblog.entity.User;

@Component
public class CommentMapper {
    public Comment toEntity(CommentCreateRequestDto requestDto, User user) {
        return Comment.builder()
                .comment(requestDto.getComment())
                .user(user)
                .build();
    }

    public CommentCreateResponseDto toCommentCreateResponseDto(Comment comment) {
        return new CommentCreateResponseDto(comment);
    }
}
