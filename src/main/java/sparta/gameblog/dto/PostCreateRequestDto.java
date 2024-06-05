package sparta.gameblog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequestDto {
    @NotBlank
    private String title;

    @NotBlank
    private String contents;
}
