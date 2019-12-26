package com.kunlong.platform.dubbo;

import com.kunlong.api.service.AuthApiService;
import com.kunlong.platform.util.SessionHolder;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
}
