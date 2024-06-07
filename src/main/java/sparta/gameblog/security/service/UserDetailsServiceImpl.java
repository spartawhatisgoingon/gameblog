package sparta.gameblog.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import sparta.gameblog.entity.User;
import sparta.gameblog.security.principal.UserPrincipal;
import sparta.gameblog.service.UserService;

@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        try {
            User user = this.userService.getUserByEmail(email);
            return new UserPrincipal(user);
        } catch (Exception e) {
            throw new UsernameNotFoundException(email +" 존재하지 않는 이메일입니다");
        }
    }
}
