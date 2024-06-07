package sparta.gameblog.security.exception;
import sparta.gameblog.jwt.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import sparta.gameblog.web.exception.errorresponse.BasicErrorResponse;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        boolean expired = JwtUtil.isExpiredAccessToken(request);
        int statusCode = expired ? 499 : HttpStatus.UNAUTHORIZED.value();
        String error = expired ? "access token 이 만료되었습니다." : HttpStatus.UNAUTHORIZED.getReasonPhrase();
        String message = expired ? "access token 이 만료되었습니다." : "username 또는 password 가 잘못되었습니다.";

        response.setStatus(statusCode);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        response.getWriter().write(
                BasicErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .path(request.getRequestURI())
                        .status(statusCode)
                        .error(error)
                        .message(message)
                        .build().toString()
        );
    }
}