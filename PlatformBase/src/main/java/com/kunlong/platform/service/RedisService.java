package com.kunlong.platform.service;

import com.kunlong.platform.model.LoginSso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 
 * @name BaseTest
 * @author ljm  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */

@Service
public class RedisService {

    private static String TOKEN_LOGIN_SSO_PRE = "session:pf:";
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, LoginSso> loginSsoRedisTemplate;

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);

    }

    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);

    }


    public void setPicCode(String ip, String code) {
        redisTemplate.expire(ip,2, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(ip, code);
    }

    public String getPicCode(String ip) {
        return getKey(ip);

    }

    public void setLoginSso(String key, LoginSso value) {
        loginSsoRedisTemplate.expire(key, 1, TimeUnit.DAYS);
        loginSsoRedisTemplate.opsForValue().set(TOKEN_LOGIN_SSO_PRE+key, value);

    }

    public Boolean checkTokenExists(String token) {
        return loginSsoRedisTemplate.opsForValue().get(TOKEN_LOGIN_SSO_PRE+token) != null;
    }

    public LoginSso getLoginSso(String key) {
        return loginSsoRedisTemplate.opsForValue().get(TOKEN_LOGIN_SSO_PRE+key) ;

    }
}
