package com.kunlong.platform.dubbo;

import cn.kunlong.center.api.model.SysUserDTO;
import com.kunlong.api.service.AuthApiService;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.util.SessionHolder;
import org.apache.dubbo.config.annotation.Service;
import org.apache.log4j.Logger;
import org.mybatis.logging.LoggerFactory;


import java.util.Map;

@Service(version = "${dubbo.service.version}",interfaceClass = AuthApiService.class)
public class AuthApiServiceProvider implements AuthApiService {
    //private static final Logger logger = LoggerFactory.getLogger(AuthApiServiceProvider.class);



    @Override
    public Boolean checkExists(String token) {
        SessionHolder session = SessionHolder.getInstance(token);
        if(!session.exists() ) {

            return false;
        } else {
            session.flush();
        }
        return true;
    }

    @Override
    public SysUserDTO getCurrentSysUser(String token) {
        Map<Object, Object> vals = this.getSessionValues(token);
        return (SysUserDTO) vals.get(SessionKeyEnum.WEB_USER.getKey());

    }

    public Map<Object,Object> getSessionValues(String key){
        Map<Object, Object> sessionValues = SessionHolder.getInstance(key).getValues();

        return sessionValues;
    }


    public Integer getCurrentUserId(String token) {
        SysUserDTO sysUserDTO = getCurrentSysUser(token);

        return sysUserDTO.getId();

    }

}
