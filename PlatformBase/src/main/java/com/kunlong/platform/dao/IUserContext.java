package com.kunlong.platform.dao;

import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.context.RestMessage.MsgRequest;

public interface IUserContext {
    Boolean getTestFlag();

    void setTestFlag(Boolean testFlag);

    LoginSso getLoginSso();

    void setLoginSso(LoginSso sso);

    default boolean isUserManager() {
        return true;//getLoginSso().isUserManager();
    }
    default boolean isTest() {
        return false;//getLoginSso().isTest();
    }

    default void checkUserRightValid(MsgRequest req) {
        //YtbContext.getSafeContext().checkUserRightValid(this, req);
    }

    default int insertUserLog(MsgRequest req) {
        return 0;//u.insertUserLog(this, req);
    }

}
