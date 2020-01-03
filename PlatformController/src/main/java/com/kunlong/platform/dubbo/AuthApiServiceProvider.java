package com.kunlong.platform.dubbo;

import cn.integriti.center.api.model.SysUserDTO;
import com.kunlong.api.service.AuthApiService;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.util.SessionHolder;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Service(version = "${dubbo.service.version}",interfaceClass = AuthApiService.class)
public class AuthApiServiceProvider implements AuthApiService {
    private static final Logger logger = LoggerFactory.getLogger(AuthApiServiceProvider.class);



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

//    @Override
//    public SysUserDTO getCurrentSysUser(String token) {
//        return null;
//    }

    public Map<Object,Object> getSessionValues(String key){
        Map<Object, Object> sessionValues = SessionHolder.getInstance(key).getValues();

        return sessionValues;
    }


    public Integer getCurrentUserId(String token) {
        Map<Object,Object> vals = this.getSessionValues(token);
        Integer userId = (Integer)vals.get(SessionKeyEnum.APP_USERID.getKey());
        return userId;

    }

}
