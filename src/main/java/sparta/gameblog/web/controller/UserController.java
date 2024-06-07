package sparta.gameblog.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sparta.gameblog.dto.UserSignupRequestDto;
import sparta.gameblog.entity.Token;
import sparta.gameblog.entity.User;
import sparta.gameblog.service.TokenService;
import sparta.gameblog.service.UserService;
import sparta.gameblog.smtp.service.SmtpService;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    private final SmtpService smtpService;

    @Qualifier("authEmailTitle")
    private final String authEmailTitle;

    @PostMapping
    public ResponseEntity<?> signup(@Valid @RequestBody UserSignupRequestDto requestDto) {
        User user = this.userService.signup(requestDto);
//        Token token = this.tokenService.createEmailValidationToken(user);
//        smtpService.sendEmail(user.getEmail(), authEmailTitle, token.getToken().toString());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
