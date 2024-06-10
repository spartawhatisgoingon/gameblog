package sparta.gameblog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.request.PostCreateRequestDto;
import sparta.gameblog.dto.request.PostPageableRequestDto;
import sparta.gameblog.dto.request.PostUpdateRequestDto;
import sparta.gameblog.dto.response.PostCreateResponseDto;
import sparta.gameblog.dto.response.PostUpdateResponseDto;
import sparta.gameblog.dto.response.PostsResponseDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.service.FollowService;
import sparta.gameblog.service.PostService;
import sparta.gameblog.smtp.service.SmtpService;
import sparta.gameblog.web.config.argumentResolver.annotation.LoginUser;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FollowService followService;
    private final SmtpService smtpService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateRequestDto requestDto, @LoginUser User currentUser) {
        PostCreateResponseDto responseDto = postService.createPost(requestDto, currentUser);
        for(User user : followService.getFollowers(currentUser)) {
            smtpService.sendEmail(user.getEmail(), user.getName() +": " + requestDto.getTitle(), responseDto.getContents());
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @GetMapping
    public ResponseEntity<?> getPosts(PostPageableRequestDto requestDto) {
        PostsResponseDto responseDto = postService.getPosts(requestDto);

        if (responseDto.getTotalElements() == 0)
            return ResponseEntity.status(HttpStatus.OK).body("먼저 작성하여 소식을 알려보세요!");

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowingPosts(PostPageableRequestDto requestDto, @LoginUser User currentUser) {
        PostsResponseDto responseDto = postService.getFollowingPosts(requestDto, currentUser);

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId, @LoginUser User currentUser) {
        postService.deletePost(postId, currentUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto requestDto, @LoginUser User currentUser) {
        PostUpdateResponseDto response = postService.updatePost(postId, requestDto, currentUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
