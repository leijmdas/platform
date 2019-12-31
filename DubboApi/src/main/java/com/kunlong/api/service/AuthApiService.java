package com.kunlong.api.service;


public interface AuthApiService {
    Boolean checkExists(String token);
    //SysUserDTO getCurrentSysUser(String token);
}
