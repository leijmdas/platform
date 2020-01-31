package com.kunlong.platform.config.auth;

import cn.kunlong.center.api.model.SysUserDTO;
import cn.kunlong.center.api.service.SysUserApiService;
import com.kunlong.platform.context.PfContext;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.exception.ApiBusinessException;
import com.kunlong.platform.tasklog.aspect.SysLoggerAnnotation;
import com.kunlong.platform.tasklog.service.LoggerService;
import com.kunlong.platform.util.SecurityUtil;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.common.utils.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
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
import java.util.List;

@Aspect
@Component
public class PreAuthAspect {
    @Reference(lazy = true, version = "${dubbo.service.version}")
    private SysUserApiService sysUserApiService;

    @Pointcut("@annotation(com.kunlong.platform.config.auth.PreAuthAnnotation)")
    public void preAuthPointCut(){

    }

    @Before("preAuthPointCut()")
    public void preAuth(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        PreAuthAnnotation annotation = method.getAnnotation(PreAuthAnnotation.class);
        SysUserDTO sysUserDTO = PfContext.getCurrentSysUser();
        if (sysUserDTO == null) {
            throw new ApiBusinessException("preAuth failed!");
        }
        if (!annotation.value().equals("admin")) {

            throw new ApiBusinessException("auth failed, you must be admin!");
        }

    }
}
