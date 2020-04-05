package com.kunlong.platform.dubbo;

import app.support.session.ISessionHolder;
import com.kunlong.dubbo.sys.model.SysUserDTO;
import com.kunlong.dubbo.api.service.AuthApiService;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.support.service.AuthService;
import com.kunlong.platform.util.SessionHolder;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Map;

@Service(version = "${dubbo.service.version}",interfaceClass = AuthApiService.class)
public class AuthApiServiceProvider implements AuthApiService {

    @Autowired
    private AuthService authService;


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

    @Override
    public AuthService.AuthToken createToken(String businessKey, String propName, Object v, long timeoutSeconds) {

        AuthService.AuthToken authToken = authService.createToken(businessKey, timeoutSeconds);
        ISessionHolder sessionHolder = SessionHolder.create(authToken.getToken());
        sessionHolder.setAttribute(propName, v);
        return authToken;
    }

    @Override
    public void setAttribute(String token, String propName, Object propValue) {
        SessionHolder sessionHolder =SessionHolder.create(token);
        sessionHolder.setAttribute(propName, propValue);

    }


    @Override
    public Object getAttribute(String token, String propName) {
        return SessionHolder.getInstance(token).getAttribute(propName);

    }

}
