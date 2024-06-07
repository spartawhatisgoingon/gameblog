package sparta.gameblog.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.CommentCreateRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.security.principal.UserPrincipal;
import sparta.gameblog.service.CommentService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") Long postId,
                                           @Valid @RequestBody CommentCreateRequestDto requestDto,
                                           @AuthenticationPrincipal UserPrincipal userPrincipal) {
        User currentUser = userPrincipal.getUser();
        return ResponseEntity.status(HttpStatus.OK).body((commentService.createComment(requestDto, postId, currentUser)));
    }

}
