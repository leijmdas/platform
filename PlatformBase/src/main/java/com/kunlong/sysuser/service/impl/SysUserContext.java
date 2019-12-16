package com.kunlong.sysuser.service.impl;


import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.dao.IUserContext;
import com.kunlong.platform.service.DefaultUserContext;

public class SysUserContext extends DefaultUserContext implements IUserContext {
    LoginSso sso;

    public LoginSso getLoginSso() {
        return sso;
    }

    public void setLoginSso(LoginSso sso) {
        this.sso = sso;
    }
}
