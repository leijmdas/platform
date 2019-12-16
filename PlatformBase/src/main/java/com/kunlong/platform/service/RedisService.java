package com.kunlong.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.URL;

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


}
