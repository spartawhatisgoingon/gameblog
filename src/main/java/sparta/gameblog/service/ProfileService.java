package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.gameblog.dto.request.ProfileRequestDto;
import sparta.gameblog.dto.response.ProfileResponseDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileResponseDto getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );

        if(!user.isActive()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        return new ProfileResponseDto(user);
    }

    @Transactional
    public ProfileResponseDto updateProfile(Long id, ProfileRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );

        if(!user.isActive()) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        String password = user.getPassword();
        if(passwordEncoder.matches(requestDto.getPassword_cur(), password)) {
            if(passwordEncoder.matches(requestDto.getPassword(), password)) {
                throw new BusinessException(ErrorCode.SAME_PASSWORD);
            }
            user.updateProfile(
                    requestDto.getName(),
                    requestDto.getIntroduction(),
                    requestDto.getPassword());
        } else {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
        return new ProfileResponseDto(user);
    }
}
