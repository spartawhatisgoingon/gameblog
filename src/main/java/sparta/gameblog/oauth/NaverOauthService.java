package sparta.gameblog.oauth;

import lombok.*;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import sparta.gameblog.dto.response.NaverProfileResponseDto;
import sparta.gameblog.dto.response.NaverTokenResponseDto;
import sparta.gameblog.jwt.JwtUtil;
import sparta.gameblog.oauth.config.NaverProperties;
import sparta.gameblog.oauth.config.UrlGenerator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NaverOauthService implements UrlGenerator {
    private final NaverProperties naverProperties;
    private final JwtUtil jwtUtil;

    @Override
    public String generateUrl() throws UnsupportedEncodingException {
        // state 는 랜덤값 생성. 지금은 사용하지말자 UUID -> 8글자 잘라서 state에 넣기

        return UriComponentsBuilder.fromHttpUrl(naverProperties.getNaverPopupUrl())
                .queryParam("response_type", "code")
                .queryParam("client_id", naverProperties.getNaverClientId())
                .queryParam("state", URLEncoder.encode(UUID.randomUUID().toString().substring(0, 8), "UTF-8"))
                .queryParam("redirect_uri", URLEncoder.encode(naverProperties.getNaverRedirectUrl(), "UTF-8"))
                .build().toUriString();
    }

    public NaverProfileResponseDto.NaverUserDetail getNaverUserDetails(String code, String state) {
        NaverTokenResponseDto tokenResponseDto = this.getToken(code, state);
        return this.getUser(tokenResponseDto.getAccess_token());
    }

    private NaverTokenResponseDto getToken(String code, String state) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(naverProperties.getNaverTokenUrl())
                .queryParam("grant_type", "authorization_code")
                .queryParam("client_id", naverProperties.getNaverClientId())
                .queryParam("client_secret", naverProperties.getNaverClientSecret())
                .queryParam("code", code)
                .queryParam("state", state)
                .build().toUriString();

        // 두번째가 인자
        // 세번째가 받아올 타입의 클래스
        // 해결: DTO 카멜 케이스 사용 -> 스네이크 케이스로 변경
        ResponseEntity<NaverTokenResponseDto> responseDto = restTemplate.postForEntity(url, null, NaverTokenResponseDto.class);

        return responseDto.getBody();
    }

    private NaverProfileResponseDto.NaverUserDetail getUser(String token) {
        RestTemplate restTemplate = new RestTemplate();

        String url = naverProperties.getNaverProfileUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(headers);
        ResponseEntity<NaverProfileResponseDto> responseDto = restTemplate.exchange(url, HttpMethod.GET, request, NaverProfileResponseDto.class);

        return responseDto.getBody().getResponse();
    }
}
