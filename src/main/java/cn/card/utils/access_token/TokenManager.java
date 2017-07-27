package cn.card.utils.access_token;

/**
 * Created by z on 2017/7/27.
 */
public interface TokenManager {

    public String createToken(String username);
    public boolean checkToken(String token);
}
