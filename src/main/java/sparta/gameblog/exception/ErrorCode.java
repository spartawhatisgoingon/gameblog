package sparta.gameblog.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    // basic
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "BAD REQUEST"),
    FORBIDDEN(HttpStatus.FORBIDDEN, "FORBIDDEN"),
    NOT_FOUND(HttpStatus.NOT_FOUND, "NOT FOUND"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "INTERNAL SERVER ERROR"),
    // post
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST NOT FOUND"),
    // user
    PASSWORD_MISMATCH(HttpStatus.UNAUTHORIZED, "PASSWORD MISMATCH"),
    SAME_PASSWORD(HttpStatus.BAD_REQUEST, "SAME PASSWORD"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER NOT FOUND"),
    EXPIRED_TOKEN(HttpStatus.BAD_REQUEST, "EXPIRED TOKEN");


    private final HttpStatus statusCode;
    private final String message;
}
