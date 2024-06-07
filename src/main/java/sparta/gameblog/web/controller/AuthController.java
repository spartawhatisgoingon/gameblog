package sparta.gameblog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.UserLoginDto;
import sparta.gameblog.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto requestDto) {
        return ResponseEntity.ok(this.authService.login(requestDto));
    }

    // social login
    @PostMapping("/login/social")
    public ResponseEntity<?> loginSocial(@RequestBody UserLoginDto requestDto) {
        /*
        문제 1.
        로그인 화면 만들어야하는지? -> 만들자

        문제 2.
        테이블을 만들어야 하는지? -> 만들지 말자. 대신 user 에 필드를 하나 추가해서, 어떤 소셜 로그인인지 타입은 넣게 수정하자

        문제 3.
        소셜로 회원가입 시 password 가 null 이라서 스키마 규칙에 어긋남 -> password 에 아무값 넣으면됨

        문제 4.


        문제
        어떻게 하는지 모르겠다.

        */
        return ResponseEntity.ok("");
    }
}
