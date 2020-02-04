package com.kunlong.platform.config.auth;

import cn.kunlong.center.api.model.SysUserDTO;
import cn.kunlong.center.api.service.SysUserApiService;
import com.kunlong.platform.context.PfContext;
import com.kunlong.platform.exception.ApiBusinessException;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
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

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //response.setStatus(402);
        if (sysUserDTO == null) {
            response.setStatus(403);
            try {
                response.getOutputStream().write("preAuth failed!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new ApiBusinessException("preAuth failed!");
        }
        if (!annotation.value().equals("admin")) {
            response.setStatus(403);
            try {
                response.getOutputStream().write("auth failed, you must be admin!".getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            throw new ApiBusinessException("auth failed, you must be admin!");
        }

    }
}
