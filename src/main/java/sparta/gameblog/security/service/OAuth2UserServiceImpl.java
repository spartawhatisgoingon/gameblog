package sparta.gameblog.security.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sparta.gameblog.dto.GoogleOAuth2UserInfo;
import sparta.gameblog.dto.NaverOAuth2UserInfo;
import sparta.gameblog.dto.OAuth2UserInfo;
import sparta.gameblog.dto.request.UserSignupRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.repository.UserRepository;
import sparta.gameblog.security.principal.UserPrincipal;
import sparta.gameblog.service.UserService;

import java.util.List;
import java.util.UUID;

@Service
public class OAuth2UserServiceImpl extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final List<OAuth2UserInfo> oAuth2UserInfoList;
    private final UserService userService;

    public OAuth2UserServiceImpl(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.oAuth2UserInfoList = List.of(new NaverOAuth2UserInfo(), new GoogleOAuth2UserInfo());
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");

        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        OAuth2UserInfo oAuth2UserInfo = oAuth2UserInfoList.stream().filter(userInfo -> userInfo.supports(provider))
                .findFirst()
                .orElseThrow(() -> new OAuth2AuthenticationException("지원하지 않는 provider"));

        User user;
        try {
            user = this.userRepository.findByEmail(oAuth2UserInfo.getEmailFromAttributes(oAuth2User.getAttributes()))
                    .orElseThrow(() -> new RuntimeException("user 없음"));
        } catch (Exception e) {
            UserSignupRequestDto userSignupRequestDto = new UserSignupRequestDto();
            userSignupRequestDto.setEmail(oAuth2UserInfo.getEmailFromAttributes(oAuth2User.getAttributes()));
            userSignupRequestDto.setName(oAuth2UserInfo.getNameFromAttributes(oAuth2User.getAttributes()));
            userSignupRequestDto.setPassword(UUID.randomUUID().toString().substring(0, 8));
            userSignupRequestDto.setRole(User.Role.NORMAL);
            userSignupRequestDto.setStatusCode(User.StatusCode.ACTIVE);
            user = this.userService.signup(userSignupRequestDto);
        }

        return new UserPrincipal(user);
    }
}
