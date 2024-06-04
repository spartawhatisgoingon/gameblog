package sparta.gameblog.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.RequestPostDto;
import sparta.gameblog.dto.ResponsePostDto;
import sparta.gameblog.service.PostService;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponsePostDto createPost(@RequestBody RequestPostDto requestDto) {
        return postService.createPost(requestDto);
    }
}
