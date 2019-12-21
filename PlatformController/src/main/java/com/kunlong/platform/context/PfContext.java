package com.kunlong.platform.context;


import com.kunlong.platform.util.RedisUtil;
import com.kunlong.platform.util.SessionHolder; 
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class PfContext implements ApplicationContextAware {
    //@Autowired
    //private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtxt = applicationContext;

        RedisUtil.Singleton.instanceRedisTemplate((RedisTemplate) appCtxt.getBean("stringRedisTemplate"));
        // RedisUtil.Singleton.instanceRedisTemplate(stringRedisTemplate);

        SessionHolder.config("session:mgr:", 7200);

    }

    public static ApplicationContext getAppCtxt() {
        return appCtxt;
    }


    private static ApplicationContext appCtxt;


}