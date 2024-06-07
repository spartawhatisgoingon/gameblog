package sparta.gameblog.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class CommentCreateRequestDto {



    @NotBlank
    private String comment;

}
