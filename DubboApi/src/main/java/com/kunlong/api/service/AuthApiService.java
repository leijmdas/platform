package com.kunlong.api.service;


import cn.kunlong.center.api.model.SysUserDTO;
import com.kunlong.platform.support.service.AuthService;

public interface AuthApiService {
    Boolean checkExists(String token);

    SysUserDTO getCurrentSysUser(String token);

    Integer getCurrentUserId(String token);

    AuthService.AuthToken createToken(String businessKey, String propName, Object v, long timeoutSeconds);

    void setAttribute(String token, String propName, Object v);

    Object getAttribute(String token, String propName);

}
