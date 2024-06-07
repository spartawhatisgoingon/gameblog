package sparta.gameblog.web.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.gameblog.dto.request.TokenRequestDto;
import sparta.gameblog.service.UserService;
import sparta.gameblog.smtp.service.SmtpService;

@RestController
@RequestMapping("/api/user/email-verification")
@RequiredArgsConstructor
public class SmtpController {

    private final SmtpService smtpService;
    private final UserService userService;

    // 이메일 받기
    @PostMapping
    public ResponseEntity<?> verificationEmail(@RequestBody TokenRequestDto requestDto) {

        // controller -> persistence context
            // smtpService -> persistence context(1) -> user 가 관리가 되고 있습니다.
            // userServic -> persistence context(2)
        //
        userService.verify(smtpService.getUserByToken(requestDto));
        return ResponseEntity.ok("hello world");
    }
}
