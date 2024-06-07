package sparta.gameblog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.CommentCreateRequestDto;
import sparta.gameblog.dto.CommentCreateResponseDto;
import sparta.gameblog.entity.Comment;
import sparta.gameblog.entity.Post;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.mapper.CommentMapper;
import sparta.gameblog.repository.CommentRepository;
import sparta.gameblog.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;


    @Transactional
    public CommentCreateResponseDto createComment(CommentCreateRequestDto requestDto, Long postId, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new BusinessException(ErrorCode.POST_NOT_FOUND)
        );
        Comment comment = commentMapper.toEntity(requestDto, currentUser);
        post.addComment(comment);
        this.commentRepository.save(comment);
        return commentMapper.toCommentCreateResponseDto(commentRepository.save(comment));
    }
}

