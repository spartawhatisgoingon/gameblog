package sparta.gameblog.dto;

import java.util.Map;

public class NaverOAuth2UserInfo implements OAuth2UserInfo {
    @Override
    public boolean supports(String providerId) {
        return "naver".equals(providerId);
    }

    @Override
    public String getEmailFromAttributes(Map<String, Object> attributes) {
        return (String) attributes.get("email");
    }

    @Override
    public String getNameFromAttributes(Map<String, Object> attributes) {
        return (String) attributes.get("name");
    }
}
