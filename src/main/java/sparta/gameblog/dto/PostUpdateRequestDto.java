package sparta.gameblog.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostUpdateRequestDto {

    @NotBlank
    private String title;

    @NotBlank
    private String contents;

}
