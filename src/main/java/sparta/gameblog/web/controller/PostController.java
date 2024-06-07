package sparta.gameblog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.*;
import sparta.gameblog.entity.Post;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.service.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body((postService.createPost(requestDto)));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }

    @GetMapping
    public ResponseEntity<?> getPosts(@RequestParam int page) {
        PostsResponseDto responseDto = postService.getPosts(page);

        if(responseDto.getData().isEmpty())
            return ResponseEntity.status(HttpStatus.OK).body("먼저 작성하여 소식을 알려보세요!");

        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{postId}")
    public ResponseEntity<?> updatePost(
            @PathVariable Long postId, @Valid @RequestBody PostUpdateRequestDto requestDto) {
        PostUpdateResponseDto response = postService.updatePost(postId, requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
