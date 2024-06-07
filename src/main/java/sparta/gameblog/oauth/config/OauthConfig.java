package sparta.gameblog.oauth.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

//public String getRequestURL() throws UnsupportedEncodingException {
//    return UriComponentsBuilder.fromHttpUrl("https://nid.naver.com/oauth2.0/authorize")
//            .queryParam("client_id", "05Z4KlzTcAyFQc74aPoM")
//            .queryParam("redirect_uri", URLEncoder.encode("http://localhost:8080/api/auth/social/login", "UTF-8"))
//            .build().toUriString();
//}

@Configuration
@RequiredArgsConstructor
public class OauthConfig {
}

