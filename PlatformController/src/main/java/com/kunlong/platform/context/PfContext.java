package com.kunlong.platform.context;


import cn.kunlong.center.api.model.SysUserDTO;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.support.service.AuthService;
import com.kunlong.platform.util.RedisUtil;
import com.kunlong.platform.util.SessionHolder; 
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
public class PfContext implements ApplicationContextAware {
    //@Autowired
    //private StringRedisTemplate stringRedisTemplate;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appCtxt = applicationContext;

        RedisUtil.Singleton.instanceRedisTemplate((RedisTemplate) appCtxt.getBean("redisTemplate"));
        //RedisUtil.Singleton.instanceRedisTemplate(stringRedisTemplate);

        SessionHolder.config("session:mgr:", AuthService.TOKEN_TIMEOUT);
    }

    public static ApplicationContext getAppCtxt() {
        return appCtxt;
    }


    private static ApplicationContext appCtxt;

    private static Map<Object, Object> getSessionValues() {
        return SessionHolder.getCurrentSessionValues();
    }

    public static SysUserDTO getCurrentSysUser() {
        Map<Object, Object> vals = getSessionValues();
        Assert.notNull(vals, "Session不存在或已效");
        SysUserDTO su = (SysUserDTO) vals.get(SessionKeyEnum.WEB_USER.getKey());
        //Assert.notNull(su, "User Session不存在或已失效");
        return su;
    }
}