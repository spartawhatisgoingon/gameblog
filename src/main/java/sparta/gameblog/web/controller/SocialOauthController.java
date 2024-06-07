package sparta.gameblog.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class SocialOauthController {
    // 1. login.html 에서 로그인 화면을 띄운다.
    // 2. 로그인을 한다. -> naver 에서 redirect url 을 준다.

    @ResponseBody
    @GetMapping("/api/auth/social/callback/naver")
    public ResponseEntity<?> socialCallback(
            @RequestParam("code") String code,
            @RequestParam("state") String state
    ) {
        System.out.println(code);
        System.out.println(state);
        return ResponseEntity.ok("naver");
    }

//    @ResponseBody

//    @GetMapping("/api/auth/lgin/social/callback/naver")
//    public ResponseEntity<?> socialCallbackFailed(
//            @RequestParam("state") String state,
//            @RequestParam("error") String error,
//            @RequestParam("error_description") String errorDescription
//    ) {
//        System.out.println(state);
//        System.out.println(error);
//        System.out.println();
//        return ResponseEntity.ok("naver");
//    }
}
