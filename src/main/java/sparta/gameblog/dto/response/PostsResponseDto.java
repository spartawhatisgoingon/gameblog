package sparta.gameblog.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostsResponseDto {
    int page;
    List<PostGetResponseDto> data;
}
