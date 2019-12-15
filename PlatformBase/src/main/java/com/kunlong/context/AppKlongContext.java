package com.kunlong.context;


import com.kunlong.service.LoginContext;
import com.kunlong.service.RedisService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppKlongContext implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtxt = applicationContext;

    }

    public static ApplicationContext getAppCtxt() {
        return appCtxt;
    }

    public static RedisService getRedisService() {
        if (redisService == null) {
            redisService = appCtxt.getBean("restTemplate", RedisService.class);

        }
        return redisService;
    }

    public static LoginContext getLoginContext() {
        if (loginContext == null) {
            loginContext = appCtxt.getBean("loginContext", LoginContext.class);

        }
        return loginContext;
    }

    private static ApplicationContext appCtxt;
    private static RedisService redisService;
    private static LoginContext loginContext;


}