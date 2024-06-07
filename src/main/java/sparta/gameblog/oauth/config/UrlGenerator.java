package sparta.gameblog.oauth.config;

import java.io.UnsupportedEncodingException;

public interface UrlGenerator {
    public String generateUrl() throws UnsupportedEncodingException;
}
