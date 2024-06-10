package sparta.gameblog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.UserLoginDto;
import sparta.gameblog.dto.request.ReIssueAccessTokenRequestDto;
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

    @PostMapping("/reissue")
    public ResponseEntity<?> reissueAccessToken(@Valid @RequestBody ReIssueAccessTokenRequestDto requestDto) {
        return ResponseEntity.ok(
                this.authService.reIssueAccessToken(requestDto)
        );
    }
}
