package sparta.gameblog.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.PostCreateRequestDto;
import sparta.gameblog.service.PostService;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody PostCreateRequestDto requestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body((postService.createPost(requestDto)));
    }
    @GetMapping("/{postId}")
    public ResponseEntity<?> getPost(@PathVariable Long postId) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPost(postId));
    }
}
