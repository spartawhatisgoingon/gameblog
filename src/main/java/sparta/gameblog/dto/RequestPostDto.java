package sparta.gameblog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class RequestPostDto {
    private String title;
    private String contents;

    public RequestPostDto (String title, String contents){
        this.title = title;
        this.contents = contents;
    }
}
