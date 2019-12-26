package com.kunlong.sysuser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.model.LoginSsoJson;
import com.kunlong.mybatis.KunlongSql;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.service.LoginContext;
import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.sysuser.model.SysUserModel;
import com.kunlong.sysuser.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Package: com.kunlong.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 20:01
 */
//
@Service
public class SysUserServiceImpl extends SysUserDAOService implements SysUserService {

    private static final Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
    @Autowired
    LoginContext loginContext;

    @Override
    public void updatePassword(String newPassword, int userId, String oldPwd) {

            StringBuilder sql = new StringBuilder(128);
            sql.append(" select * from ytb_manager.sys_user");
            sql.append(" where userId=").append(userId);
            sql.append(" where password=").append(oldPwd);
            SysUserModel sysUserModel = KunlongSql.selectOne(sql, SysUserModel.class);
            if (sysUserModel != null) {
                updatePassword(newPassword, userId);
            }

    }

    //判断后台用户登录
    @Override
    public Map<String, Object> checkUserByUserName(String userName, String password, String ip) {

        SysUserModel sysUserModel = getUserByUserNameModel(userName);
        checkUserInfo(sysUserModel, password, 1);
        return saveLoginInfo(sysUserModel, ip);

    }


    @Override
    public Map<String, Object> checkUserByMobile(String mobile, String ip) {

        SysUserModel sysUserModel = getUserByMobileModel(mobile);
        checkUserInfo(sysUserModel, null, 2);
        return saveLoginInfo(sysUserModel, ip);
    }


    void checkUserInfo(SysUserModel sysUserModel, String password, Integer loginType) {
        if (sysUserModel == null) {
            throw new KunlongError(KunlongError.CODE_INVALID_USER);
        }
        if (!sysUserModel.getStatus()) {
            throw new KunlongError(KunlongError.CODE_INVALID_USER);
        }
        if (loginType == 1 && !DigestUtils.md5Hex(password).equals(sysUserModel.getPassword())) {
            throw new KunlongError(KunlongError.CODE_INVALID_USER);
        }

    }

    Map<String, Object> saveLoginInfo(SysUserModel sysUserModel, String ip) {

        String token = KunlongUtils.getUUID(true);
        String refresh_token = KunlongUtils.getUUID(true);
        LoginSso loginSso = new LoginSso();
        loginSso.setUserid(sysUserModel.getUserId());
        loginSso.setToken(token);
        loginSso.setLoginIp(ip);
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("userType", LoginSsoJson.USER_TYPE_MANAGER);//sysuser
        body.put("testFlag", sysUserModel.getTestFlag());
        body.put("token", token);
        body.put("refresh_token", refresh_token);
        body.put("userId", sysUserModel.getUserId());

        body.put("expires_in", 300);
        body.put("bangbang_no", sysUserModel.getBangbangNo());
        body.put("nickName", sysUserModel.getUserName());
        body.put("userName", sysUserModel.getUserName());
        body.put("login_mobile", sysUserModel.getMobile());
        loginSso.setJson(JSONObject.toJSONString(body));
        loginSso.setLoginTime(new Date());
        loginContext.login(token, loginSso);
        logger.debug("token:{}", loginSso);
        logger.info("token:{}", loginSso);
        return body;
    }

}

