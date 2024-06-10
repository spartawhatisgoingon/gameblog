package sparta.gameblog.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.LoginTokenDto;
import sparta.gameblog.dto.response.NaverProfileResponseDto;
import sparta.gameblog.dto.request.UserSignupRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.jwt.JwtUtil;
import sparta.gameblog.service.UserService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverUserService {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public LoginTokenDto naverLogin(NaverProfileResponseDto.NaverUserDetail naverUserDetail) {
        User user = null;

        try {
            user = userService.getUserByEmail(naverUserDetail.getEmail());
        } catch (Exception e) {
            UserSignupRequestDto dto = new UserSignupRequestDto();
            dto.setEmail(naverUserDetail.getEmail());
            dto.setName(naverUserDetail.getNickname());
            dto.setPassword(UUID.randomUUID().toString().substring(0, 8));
            user = userService.signup(dto);
        }

        return LoginTokenDto.builder()
                .accessToken(jwtUtil.createAccessToken(user))
                .build();
    }
}
