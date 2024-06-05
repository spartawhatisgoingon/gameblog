package sparta.gameblog.web.exception.errorresponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class BasicErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    @Override
    public String toString() {
        return String.format("""
                        {
                            "timestamp": "%s",
                            "status": %d,
                            "error": "%s",
                            "message": "%s",
                            "path": "%s"
                        }
                        """,
                LocalDateTime.now(),
                this.status,
                this.error,
                this.message,
                this.path
        );
    }
}
