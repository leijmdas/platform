package com.kunlong.platform.service;

import com.alibaba.fastjson.JSON;
import com.kunlong.platform.model.LoginSso;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;
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

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
//    @Autowired
//    private RedisTemplate<String, LoginSso> loginRedisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
        redisTemplate.boundListOps(userId).leftPush(url.toExternalForm());
    }

    public void setKey(String key, String value) {
        redisTemplate.opsForValue().set(key, value);

    }

    public String getKey(String key) {
        return redisTemplate.opsForValue().get(key);

    }

    public void setLoginSso(String key, LoginSso value) {
        redisTemplate.expire(key,1, TimeUnit.DAYS);
        redisTemplate.opsForValue().set(key, value.toString());

    }

    public Boolean checkTokenExists(String koken) {
        return redisTemplate.opsForValue().get(koken) != null;
    }

    public LoginSso getLoginSso(String key) {
        return JSON.parseObject(redisTemplate.opsForValue().get(key), LoginSso.class);

    }
}
