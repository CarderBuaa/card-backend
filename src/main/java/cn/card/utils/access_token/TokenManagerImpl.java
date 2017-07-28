package cn.card.utils.access_token;


import com.mysql.jdbc.StringUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Description: 用于安全验证,将token存储到JVM内存中
 * Created by z on 2017/7/27.
 */
public class TokenManagerImpl implements TokenManager{

    private static Map<String, String> tokenMap = new ConcurrentHashMap<>();

    @Override
    public String createToken(String username) {

        String token = UUID.randomUUID().toString();
        //将token放到内存中
        tokenMap.put(token, username);
        return token;
    }

    @Override
    public boolean checkToken(String token) {

        return !StringUtils.isNullOrEmpty(token) && tokenMap.containsKey(token);
    }
}
