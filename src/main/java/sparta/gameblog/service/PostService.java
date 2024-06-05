package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.PostCreateRequestDto;
import sparta.gameblog.dto.PostCreateResponseDto;
import sparta.gameblog.dto.PostGetResponseDto;
import sparta.gameblog.dto.PostsResponseDto;
import sparta.gameblog.entity.Post;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.mapper.PostMapper;
import sparta.gameblog.repository.PostRepository;

import java.util.List;

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
                () -> (new BusinessException(ErrorCode.POST_NOT_FOUND))
        );
        return new PostGetResponseDto(post);
    }

    public PostsResponseDto getPosts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Post> posts = postRepository.findAll(pageable);
        List<PostGetResponseDto> postGetResponseDtoList = posts.map(PostGetResponseDto::new).getContent();

        if(posts.getTotalElements() > 0 && postGetResponseDtoList.isEmpty()) {
            throw new BusinessException(ErrorCode.POST_NOT_FOUND);
        }

        return PostsResponseDto.builder().page(page)
                .data(postGetResponseDtoList)
                .build();
    }
}
