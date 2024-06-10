package sparta.gameblog.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.request.FollowRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.service.FollowService;
import sparta.gameblog.web.config.argumentResolver.annotation.LoginUser;

@RestController
@RequestMapping("/api/follow")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping
    public ResponseEntity<?> follow(@RequestBody FollowRequestDto requestDto, @LoginUser User currentUser) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followService.follow(requestDto, currentUser));
    }

    @DeleteMapping
    public ResponseEntity<?> unfollow(@RequestBody FollowRequestDto requestDto, @LoginUser User currentUser) {
        followService.unfollow(requestDto, currentUser);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
