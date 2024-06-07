package sparta.gameblog.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginDto {
    @Email
    private String email;

    @NotBlank
    private String password;
}
