package cn.card.utils.access_token;


import com.mysql.jdbc.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Description: 用于安全验证
 * Created by z on 2017/7/27.
 */
public class TokenManagerImpl implements TokenManager{

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String createToken(String username) {
        String token = UUID.randomUUID().toString();

        tokenMap.put(token, username);
        return token;
    }

    @Override
    public boolean checkToken(String token) {

        return !StringUtils.isEmptyOrWhitespaceOnly(token) && tokenMap.containsKey(token);
    }
}
