package com.kunlong.platform.tasklog.aspect;

import cn.kunlong.center.api.model.SysUserDTO;
import com.kunlong.platform.context.PfContext;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.tasklog.service.LoggerService;
import com.kunlong.platform.util.SecurityUtil;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
public class SysLoggerAspect {
    @Autowired
    private LoggerService loggerService;

    @Pointcut("@annotation(com.kunlong.platform.tasklog.aspect.SysLoggerAnnotation)")
    public void loggerPointCut(){

    }

    @Before("loggerPointCut()")
    public void saveSysLog(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        SysLoggerAnnotation annotation = method.getAnnotation(SysLoggerAnnotation.class);
        HttpServletRequest req = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Tasklog log = new Tasklog();
        log.setCode(0);

        log.setIp(KunlongUtils.getIpAddr(req));
        log.setOprt(method.getName());
        log.setOprtTime(new Date());
        SysUserDTO sysUserDTO = PfContext.getCurrentSysUser();
        String[] p = new String[joinPoint.getArgs().length];
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            p[i] = joinPoint.getArgs()[i].toString();
        }
        if(annotation!=null&&annotation.security()){
            log.setParams(SecurityUtil.md5( StringUtils.join(p, ",")));

        }
        else{
            log.setParams(StringUtils.join(p, ","));
            if(log.getParams().length()>512){
                log.setParams(log.getParams().substring(0,512));
            }
        }

        String username = "";
        if (p.length > 0) {
            username = p[0].toString();
        }
        log.setUser(sysUserDTO == null ? username : sysUserDTO.getUsername());
        log.setOprtType((byte) 0);
        loggerService.log(log);
    }
}
