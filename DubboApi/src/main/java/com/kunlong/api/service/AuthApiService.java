package com.kunlong.api.service;


//import cn.integriti.center.api.model.SysUserDTO;

public interface AuthApiService {
    Boolean checkExists(String token);
     //  SysUserDTO getCurrentSysUser(String token);
     Integer getCurrentUserId(String token) ;

 }
