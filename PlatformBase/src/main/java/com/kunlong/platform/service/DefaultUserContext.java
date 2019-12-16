package com.kunlong.platform.service;

import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.dao.IUserContext;
import com.kunlong.platform.model.KunlongModel;

public class DefaultUserContext extends KunlongModel implements IUserContext {
    LoginSso sso;
    Boolean testFlag;


    @Override
    public Boolean getTestFlag() {
        return testFlag;
    }

    public void setTestFlag(Boolean testFlag) {
        this.testFlag = testFlag;
    }


    @Override
    public LoginSso getLoginSso() {
        return sso;
    }

    @Override
    public void setLoginSso(LoginSso sso) {
        this.sso = sso;

    }
}
