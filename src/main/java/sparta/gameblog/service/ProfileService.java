package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

        if(user.getStatusCode() != User.StatusCode.ACTIVE) {
            throw new BusinessException(ErrorCode.USER_NOT_FOUND);
        }

        return new ProfileResponseDto(user);
    }
}
