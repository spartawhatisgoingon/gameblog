package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.PostCreateRequestDto;
import sparta.gameblog.dto.PostCreateResponseDto;
import sparta.gameblog.dto.PostGetResponseDto;
import sparta.gameblog.entity.Post;
import sparta.gameblog.mapper.PostMapper;
import sparta.gameblog.repository.PostRepository;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostCreateResponseDto createPost(PostCreateRequestDto requestDto) {
        Post post = postMapper.toEntity(requestDto);
        return new PostCreateResponseDto(postRepository.save(post));
    }

    public PostGetResponseDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> (new IllegalArgumentException("postId에 맞는 게시글이 존재하지 않습니다."))
        );
        return new PostGetResponseDto(post);
    }
}
