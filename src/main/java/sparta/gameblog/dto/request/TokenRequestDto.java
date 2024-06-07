package sparta.gameblog.dto.request;

import lombok.Getter;

import java.util.UUID;

@Getter
public class TokenRequestDto {
    private UUID token;
}
