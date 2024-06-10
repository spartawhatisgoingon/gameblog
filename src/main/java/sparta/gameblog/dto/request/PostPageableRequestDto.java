package sparta.gameblog.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PostPageableRequestDto {
    private int page;
    private String sort;
    private String search;
    private LocalDate startDate;
    private LocalDate endDate;
}
