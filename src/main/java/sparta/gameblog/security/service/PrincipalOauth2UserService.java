package sparta.gameblog.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import sparta.gameblog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Assert.notNull(userRequest, "userRequest cannot be null");
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String loginId = provider + ":" + providerId;
        // oath2 99%
        // google / naver
        // google -> oauth2 이미 다 설정해놓음
        // naver --> provider 를 직접 지정 -> toekn & profile uri
//
//        Optional<User> optionalUser = userRepository.findById();
//
//        OAuth2AccessToken token = userRequest.getAccessToken();
//        Map<String, Object> attributes = (Map)((Converter)this.attributesConverter.convert(userRequest)).convert((Map)response.getBody());
//        Collection<GrantedAuthority> authorities = this.getAuthorities(token, attributes);
//        return new DefaultOAuth2User(authorities, attributes, userNameAttributeName);
        return null;
    }
}
