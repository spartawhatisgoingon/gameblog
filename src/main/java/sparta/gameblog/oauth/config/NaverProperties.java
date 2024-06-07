package sparta.gameblog.oauth.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NaverProperties {
    @Value("${app.social.naver.clientid}")
    private String naverClientId;

    @Value("${app.social.naver.clientsecret}")
    private String naverClientSecret;

    @Value("${app.social.naver.redirecturl}")
    private String naverRedirectUrl;

    @Value("${app.social.naver.popupurl}")
    private String naverPopupUrl;

    @Value("${app.social.naver.tokenurl}")
    private String naverTokenUrl;

    @Value("${app.social.naver.profileurl}")
    private String naverProfileUrl;

}
