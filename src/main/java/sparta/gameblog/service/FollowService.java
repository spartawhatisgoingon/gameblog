package sparta.gameblog.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sparta.gameblog.dto.request.FollowRequestDto;
import sparta.gameblog.dto.response.FollowResponseDto;
import sparta.gameblog.entity.Follow;
import sparta.gameblog.entity.User;
import sparta.gameblog.exception.BusinessException;
import sparta.gameblog.exception.ErrorCode;
import sparta.gameblog.mapper.FollowMapper;
import sparta.gameblog.repository.FollowRepository;
import sparta.gameblog.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final FollowMapper followMapper;

    public FollowResponseDto follow(FollowRequestDto requestDto, User currentUser) {
        User followingUser = checkFollow(requestDto, currentUser);

        if(followRepository.findByFollowingUserIdAndFollowerUserId(followingUser.getId(), currentUser.getId()).isPresent()) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        Follow follow = followMapper.toEntity(followingUser, currentUser);
        return new FollowResponseDto(followRepository.save(follow));
    }

    public void unfollow(FollowRequestDto requestDto, User currentUser) {
        User followingUser = checkFollow(requestDto, currentUser);

        Follow follow = followRepository.findByFollowingUserIdAndFollowerUserId(followingUser.getId(), currentUser.getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        followRepository.delete(follow);
    }

    public List<User> getFollowers(User currentUser) {
        return followRepository.findAllByFollowingUserId(currentUser.getId()).stream().map(Follow::getFollowerUser).toList();
    }

    private User checkFollow(FollowRequestDto requestDto, User currentUser) {
        User followingUser = userRepository.findById(requestDto.getFollowingUserId()).orElseThrow(
                () -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        if(followingUser.getId() == currentUser.getId()) {
            throw new BusinessException(ErrorCode.BAD_REQUEST);
        }

        return followingUser;
    }
}
