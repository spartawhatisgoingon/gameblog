package sparta.gameblog.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileRequestDto {
    
    private String name;

    private String introduction;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,30}$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    private String password_cur;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{10,30}$",
            message = "비밀번호 형식이 올바르지 않습니다.")
    private String password;
}
