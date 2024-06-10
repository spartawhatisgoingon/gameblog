package sparta.gameblog.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.request.CommentCreateRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.security.principal.UserPrincipal;
import sparta.gameblog.service.CommentService;
import sparta.gameblog.web.config.argumentResolver.annotation.LoginUser;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<?> createComment(@PathVariable("postId") Long postId,
                                           @Valid @RequestBody CommentCreateRequestDto requestDto,
                                           @LoginUser User currentUser) {
        return ResponseEntity.status(HttpStatus.OK).body((commentService.createComment(requestDto, postId, currentUser)));
    }


    @GetMapping("/post/{commentId}/comment")
    public ResponseEntity<?> getComment(@PathVariable Long commentId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getComment(commentId));
    }

    @PutMapping("/post/{commentId}/comment")
    public ResponseEntity<?> updateComment(@PathVariable Long commentId,
                                           @Valid @RequestBody CommentCreateRequestDto requestDto,
                                           @LoginUser User currentUser) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentId, requestDto, currentUser));
    }

    @DeleteMapping("/post/{commentId}/comment")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
