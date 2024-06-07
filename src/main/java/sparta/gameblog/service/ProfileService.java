package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sparta.gameblog.dto.ProfileRequestDto;
import sparta.gameblog.dto.ProfileResponseDto;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public ProfileResponseDto getProfile(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
        validateUser(user);

        return new ProfileResponseDto(user);
    }

    @Transactional
    public ProfileResponseDto updateProfile(Long id, ProfileRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
        validateUser(user);

        if(user.getPassword().equals(requestDto.getPassword_cur())) {
            if(user.getPassword().equals(requestDto.getPassword())) {
                throw new BusinessException(ErrorCode.SAME_PASSWORD);
            }
            user.updateProfile(
                    requestDto.getName(),
                    requestDto.getEmail(),
                    requestDto.getIntroduction(),
                    requestDto.getPassword());
        } else {
            throw new BusinessException(ErrorCode.PASSWORD_MISMATCH);
        }
        return new ProfileResponseDto(user);
    }

    private void validateUser(User user) {
        if(user.getStatusCode() != User.StatusCode.ACTIVE) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }
    }
}
