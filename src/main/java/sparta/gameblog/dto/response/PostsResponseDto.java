package sparta.gameblog.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostsResponseDto {
    @JsonProperty("total_elements")
    Long totalElements;
    int page;
    List<PostGetResponseDto> data;
}
