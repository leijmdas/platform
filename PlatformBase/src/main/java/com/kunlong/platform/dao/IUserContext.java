package com.kunlong.platform.dao;

import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.context.RestMessage.MsgRequest;

public interface IUserContext {
    Boolean getTestFlag();

    void setTestFlag(Boolean testFlag);

    LoginSso getLoginSso();

    void setLoginSso(LoginSso sso);

    default boolean isUserManager() {
        return true;
    }

    default boolean isTest() {
        return false;
    }

    default void checkUserRightValid(MsgRequest req) {
        if(req.isLoginCmd()){
            return ;
        }
        if (AppKlongContext.getLoginContext().checkTokenExists(req.token)) {
            return;
        }
        throw new KunlongError(KunlongError.CODE_INVALID_USER);

        //YtbContext.getSafeContext().checkUserRightValid(this, req);
    }

    default int insertUserLog(MsgRequest req) {
        return 0;
    }

}
