package sparta.gameblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignupRequestDto {
    @Email
    @NotBlank
    private String email;


    @NotBlank
    // @Pattern(regexp = "{10,30}", message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    @NotBlank
    private String name;


}
