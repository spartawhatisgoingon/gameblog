package sparta.gameblog.security.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import sparta.gameblog.exception.ErrorCode;

@Getter
@RequiredArgsConstructor
public class HttpStatusException extends RuntimeException {
    private final ErrorCode errorCode;
}