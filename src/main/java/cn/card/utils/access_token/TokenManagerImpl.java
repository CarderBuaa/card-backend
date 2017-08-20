package cn.card.utils.access_token;


import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


/**
 * Description: 用于安全验证
 * Created by z on 2017/7/27.
 */
public class TokenManagerImpl implements TokenManager{

    private JedisPool jedisPool;

    @Autowired
    public void setJedisPool(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    @Override
    public String createToken(String username) {

        String token = UUID.randomUUID().toString();
        //将token放入redis中
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(token, username);
            //设置token的超时时间为一个小时
            jedis.expire(token, 3600);
        }finally {
            if(jedis != null)
                jedis.close();
        }
        return token;
    }

    @Override
    public boolean checkToken(String token) {
        //未授权
        boolean check = false;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            if(jedis.exists(token)){
                check = true;
            }
        }finally {
            if(jedis != null)
                jedis.close();
        }

        return !StringUtils.isNullOrEmpty(token) && check;
    }

    @Override
    public String getUsername(String token) {
        String username = null;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            if(jedis.exists(token)){
                username = jedis.get(token);
            }
        }finally {
            if(jedis != null)
                jedis.close();
        }

        return username;
    }
}
