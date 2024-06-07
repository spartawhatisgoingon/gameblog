package sparta.gameblog.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sparta.gameblog.dto.response.NaverProfileResponseDto;
import sparta.gameblog.oauth.NaverOauthService;
import sparta.gameblog.oauth.NaverUserService;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

@RestController
@RequiredArgsConstructor
public class SocialOauthController {

    private final NaverOauthService naverOauthService;
    private final NaverUserService naverUserService;

    @ResponseBody
    @GetMapping("/api/auth/social/login")
    public ResponseEntity<?> socialCallback(
            @RequestParam("code") String code,
            @RequestParam("state") String state
    ) {
        NaverProfileResponseDto.NaverUserDetail detail = this.naverOauthService.getNaverUserDetails(code, state);
        return ResponseEntity.ok(naverUserService.naverLogin(detail));
    }

    @GetMapping("/naver/login")
    public void naverLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(naverOauthService.generateUrl());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
