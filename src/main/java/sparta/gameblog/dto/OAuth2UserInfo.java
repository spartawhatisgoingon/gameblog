package sparta.gameblog.dto;

public interface OAuth2UserInfo {
    String getProvider();
    String getEmail();
    String getName();
}
