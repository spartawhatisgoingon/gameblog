package sparta.gameblog.dto;

import lombok.Getter;

@Getter
public class PostCreateRequestDto {
    private String title;
    private String contents;

    public PostCreateRequestDto(String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
