package sparta.gameblog.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sparta.gameblog.constants.SortType;
import sparta.gameblog.dto.request.PostCreateRequestDto;
import sparta.gameblog.dto.request.PostPageableRequestDto;
import sparta.gameblog.dto.request.PostUpdateRequestDto;
import sparta.gameblog.dto.response.PostCreateResponseDto;
import sparta.gameblog.dto.response.PostGetResponseDto;
import sparta.gameblog.dto.response.PostUpdateResponseDto;
import sparta.gameblog.dto.response.PostsResponseDto;
import sparta.gameblog.entity.Post;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.mapper.PostMapper;
import sparta.gameblog.repository.PostRepository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final FollowService followService;

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

    public PostsResponseDto getPosts(PostPageableRequestDto requestDto) {
        PostQueryParams queryParams = new PostQueryParams(requestDto);

        Page<Post> posts = postRepository.findByTitleContainingAndCreatedAtBetween(
                queryParams.getSearch(),
                queryParams.getStartDate(),
                queryParams.getEndDate(),
                queryParams.getPageable());

        List<PostGetResponseDto> postGetResponseDtoList = posts.map(PostGetResponseDto::new).getContent();

        return PostsResponseDto.builder()
                .totalElements(posts.getTotalElements())
                .page(requestDto.getPage())
                .data(postGetResponseDtoList)
                .build();
    }

    public PostsResponseDto getFollowingPosts(PostPageableRequestDto requestDto, User currentUser) {
        PostQueryParams queryParams = new PostQueryParams(requestDto);

        List<User> followings = followService.getFollowings(currentUser);
        Page<Post> posts = postRepository.findByTitleContainingAndCreatedAtBetweenAndUserIn(
                queryParams.getSearch(),
                queryParams.getStartDate(),
                queryParams.getEndDate(),
                followings,
                queryParams.getPageable());

        List<PostGetResponseDto> postGetResponseDtoList = posts.map(PostGetResponseDto::new).getContent();

        return PostsResponseDto.builder()
                .totalElements(posts.getTotalElements())
                .page(requestDto.getPage())
                .data(postGetResponseDtoList)
                .build();
    }

    public void deletePost(Long postId, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> (new RuntimeException("postId에 맞는 게시글이 존재하지 않습니다."))
        );
        if (!post.canUpdateBy(currentUser)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }
        postRepository.delete(post);
    }

    @Transactional
    public PostUpdateResponseDto updatePost(Long postId, PostUpdateRequestDto requestDto, User currentUser) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> (new BusinessException(ErrorCode.POST_NOT_FOUND))
        );
        if (!post.canUpdateBy(currentUser)) {
            throw new BusinessException(ErrorCode.FORBIDDEN);
        }

        post.update(postMapper.toEntity(requestDto));

        return new PostUpdateResponseDto(post);
    }

    @Getter
    private static class PostQueryParams {
        private final Pageable pageable;
        private final String search;
        private final LocalDateTime startDate;
        private final LocalDateTime endDate;

        public PostQueryParams(PostPageableRequestDto requestDto) {
            SortType sortType = SortType.fromColumn(requestDto.getSort());
            Sort sort = Sort.by(sortType.getColumn());
            pageable = PageRequest.of(requestDto.getPage(), 10, sort);

            search = requestDto.getSearch() != null ? requestDto.getSearch() : "";
            startDate = requestDto.getStartDate() != null ?
                    requestDto.getStartDate().atStartOfDay() : LocalDateTime.of(1960, 1, 1, 0, 0);
            endDate = requestDto.getEndDate() != null ?
                    requestDto.getEndDate().atTime(LocalTime.MAX) : LocalDateTime.of(2111, 1, 1, 0, 0);
        }
    }
}
