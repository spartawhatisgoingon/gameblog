package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.gameblog.dto.UserSignupRequestDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.mapper.UserMapper;
import sparta.gameblog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User signup(UserSignupRequestDto requestDto) {
        User user = this.userMapper.toEntity(requestDto);
        return this.userRepository.save(user);
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("user 없음"));
    }

    @Transactional
    public void verify(User user) {
        user.verify();
    }
}
