package sparta.gameblog.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sparta.gameblog.dto.GoogleUserInfo;
import sparta.gameblog.dto.NaverUserInfo;
import sparta.gameblog.dto.OAuth2UserInfo;
import sparta.gameblog.dto.request.UserSignupRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.repository.UserRepository;
import sparta.gameblog.security.principal.UserPrincipal;
import sparta.gameblog.service.UserService;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrincipalOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");

        OAuth2User oAuth2User = super.loadUser(userRequest);
        OAuth2UserInfo oAuth2UserInfo = null;

        String provider = userRequest.getClientRegistration().getRegistrationId();
        if(provider.equals("google")) {
            oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
        } else if(provider.equals("naver")) {
            oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
        }

        // UserService 거치지 않는 이유 -> PasswordEncorder로 인한 순환 참조 발생
        User user = null;
        try {
            user = this.userRepository.findByEmail(oAuth2UserInfo.getEmail())
                    .orElseThrow(() -> new RuntimeException("user 없음"));
        } catch (Exception e) {
            user = User.builder()
                    .email(oAuth2UserInfo.getEmail())
                    .name(oAuth2UserInfo.getName())
                    .password(UUID.randomUUID().toString().substring(0, 8))
                    .statusCode(User.StatusCode.ACTIVE)
                    .role(User.Role.NORMAL)
                    .build();
            this.userRepository.save(user);
        }

        return new UserPrincipal(user);
    }
}
