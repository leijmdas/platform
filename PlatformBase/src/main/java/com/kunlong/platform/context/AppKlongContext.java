package com.kunlong.platform.context;


import com.kunlong.platform.service.LoginContext;
import com.kunlong.platform.service.RedisService;
import com.kunlong.platform.service.impl.ErrorCodeService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AppKlongContext implements ApplicationContextAware {
//    @Autowired
//    LoginContext loginContext;

    public static ErrorCodeService getErrorCodeService() {
        return appCtxt.getBean("errorCodeService", ErrorCodeService.class);

    }


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


    public static SqlSessionFactory getSqlSessionFactory(){
        return appCtxt.getBean("primarySqlSessionFactory", SqlSessionFactory.class);

    }

    private static ApplicationContext appCtxt;
    private static RedisService redisService;
    private static LoginContext loginContext;


}