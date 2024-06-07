package sparta.gameblog.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    // 1. security
    // 2. password -> encrypt
    // 3. security 기본으로 제공하는 로그인 방법
    //   - form login -> api -> login controller -> access token
    //   - basic -> Authorization: Bearer jwtotken ->
    //s

    // 1. access token 발급 -> 컨트롤러
    // 2. 매 요청마다 header 를 확인해서 로그인 정보를 확인해야한다. -> filter

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.httpBasic().disable();
        http.formLogin().disable();

        http.sessionManagement(configurer ->
            configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        // TODO:
        // 1. 로그인 아이디 / 패스워드 틀렸을 때 예외 401
        // 2. 보안레벨 맞지 않을 때 예외 403
//        http.exceptionHandling(e -> e
//                .authenticationEntryPoint() // 401
//                .accessDeniedHandler()
//        )  // 403

        http.authorizeHttpRequests(requests ->
//                requests.requestMatchers(HttpMethod.DELETE, "/api/user").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/api/auth/reissue").authenticated()
//                        .requestMatchers(HttpMethod.PUT, "/api/user/{id}").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/api/post").authenticated()
//                        .requestMatchers(HttpMethod.PUT, "/api/post").authenticated()
//                        .requestMatchers(HttpMethod.POST, "/api/user/email-verification").anonymous()
                requests.anyRequest().anonymous()
        );

        return http.build();
    }
}