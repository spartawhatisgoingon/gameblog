package sparta.gameblog.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import sparta.gameblog.entity.User;

@Getter
@Setter
@ToString
public class UserSignupRequestDto {
    @Email
    @NotBlank
    private String email;


    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,30}$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;

    @NotBlank
    private String name;

    private User.Role role = User.Role.NORMAL;

    private User.StatusCode statusCode = User.StatusCode.INACTIVE;
}
