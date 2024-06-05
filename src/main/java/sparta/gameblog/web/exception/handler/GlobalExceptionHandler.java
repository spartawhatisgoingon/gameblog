package sparta.gameblog.web.exception.handler;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.web.exception.errorresponse.BasicErrorResponse;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<BasicErrorResponse> handleBusinessException(BusinessException ex, HttpServletRequest request) {
        return ResponseEntity.status(ex.getStatusCode()).body(
                BasicErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .error(ex.getStatusCode().getReasonPhrase())
                        .status(ex.getStatusCode().value())
                        .message(ex.getMessage())
                        .path(request.getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler
    ResponseEntity<BasicErrorResponse> handleBeanValidationException(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String errorMessage = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    FieldError fieldError = (FieldError) error;
                    return String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
                })
                .collect(Collectors.joining("\n"));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                BasicErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .error(HttpStatus.BAD_REQUEST.getReasonPhrase())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .message(errorMessage)
                        .path(request.getRequestURI())
                        .build()
        );
    }

}
