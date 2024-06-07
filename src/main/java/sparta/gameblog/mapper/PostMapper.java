package sparta.gameblog.mapper;

import org.springframework.stereotype.Component;
import sparta.gameblog.dto.PostCreateRequestDto;
import sparta.gameblog.dto.PostCreateResponseDto;
import sparta.gameblog.entity.Post;
import sparta.gameblog.entity.User;

@Component
public class PostMapper {
    public Post toEntity(PostCreateRequestDto requestDto, User user) {
        return Post.builder()
                .user(user)
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .build();
    }

    public PostCreateResponseDto toPostCreateResponseDto(Post post) {
        return new PostCreateResponseDto(post);
    }
}
