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

    @Transactional
    public void verify(User user) {
        user.verify();
    }
}
