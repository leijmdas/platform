package com.kunlong.sysuser.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.kunlong.model.LoginSso;
import com.kunlong.model.LoginSsoJson;
import com.kunlong.mybatis.KunlongSql;
import com.kunlong.mybatis.SqlSessionBuilder;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.sysuser.dao.SysUserMapper;
import com.kunlong.sysuser.model.SysUserModel;
import com.kunlong.sysuser.service.SysUserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.ibatis.session.SqlSession;


import java.util.*;

/**
 * Package: ytb.manager.sysuser.service.impl
 * Author: ZCS
 * Date: Created in 2018/8/21 20:01
 */
public class SysUserServiceImpl extends SysUserDAOService implements SysUserService, SysUserMapper {
    SqlSessionBuilder sqlSessionBuilder=new SqlSessionBuilder();
    @Override
    public void updatePassword(String newPassword, int userId, String oldPwd) {
        try (SqlSession ss = sqlSessionBuilder.getSession_manager(true)) {
            StringBuilder sql = new StringBuilder(128);
            sql.append(" select * from ytb_manager.sys_user");
            sql.append(" where userId=").append(userId);
            sql.append(" where password=").append(oldPwd);
            SysUserModel sysUserModel = KunlongSql.selectOne(sql, SysUserModel.class);
            if (sysUserModel != null) {
                SysUserMapper userDao = ss.getMapper(SysUserMapper.class);
                userDao.updatePassword(newPassword, userId);
            }

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
        YtbContext.getYtb_context().getSafeContext().save2DB(token, loginSso);

        return body;
    }

}

