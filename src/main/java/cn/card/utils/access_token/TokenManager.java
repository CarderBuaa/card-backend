package cn.card.utils.access_token;

/**
 * Created by z on 2017/7/27.
 */
public interface TokenManager {

    String createToken(String username);
    boolean checkToken(String token);
    String getUsername(String token);
}
