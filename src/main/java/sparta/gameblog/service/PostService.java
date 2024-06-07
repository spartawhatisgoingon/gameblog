package sparta.gameblog.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.*;
import sparta.gameblog.entity.Post;
import sparta.gameblog.entity.User;
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

    @Transactional
    public PostCreateResponseDto createPost(PostCreateRequestDto requestDto, User currentUser) {
        Post post = postMapper.toEntity(requestDto, currentUser);
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

    public void deletePost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> (new RuntimeException("postId에 맞는 게시글이 존재하지 않습니다."))
        );
        postRepository.delete(post);
    }

    public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> (new BusinessException(ErrorCode.POST_NOT_FOUND))
        );
        post.update(requestDto.getTitle(), requestDto.getContents());
        postRepository.save(post);

        return new PostUpdateResponseDto(post);
    }
}
