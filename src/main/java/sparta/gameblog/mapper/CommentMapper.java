package sparta.gameblog.mapper;

import org.springframework.stereotype.Component;
import sparta.gameblog.dto.request.CommentCreateRequestDto;
import sparta.gameblog.dto.response.CommentCreateResponseDto;
import sparta.gameblog.entity.Comment;
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
