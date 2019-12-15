package com.kunlong.platform.controller.web.sysuser.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kunlong.model.Api_KeyModel;
import com.kunlong.model.LoginSso;
import com.kunlong.model.LoginSsoJson;
import com.kunlong.platform.context.RestMessage.MsgHandler;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.dao.IUserContext;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.service.SafeContext;
import com.kunlong.sysuser.model.SysUserModel;
import com.kunlong.sysuser.service.SysUserService;
import com.kunlong.sysuser.service.impl.SysUserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Package: com.kunlong.sysuser.sysuser.impl
 * Author: ZCS
 * Date: Created in 2018/8/22 14:11
 */
public class SysUser {

    static String default_pwd = "www.163.com";
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = "{}";

    int insertUserLog(MsgRequest req, int userId) {
//        Tasklog_UserServiceImpl us = new Tasklog_UserServiceImpl();
//        Tasklog_UserModel m = new Tasklog_UserModel();
//        m.setUserId(userId);
//        m.setUserIp(req.msgBody.getString("ip"));
//        m.setOprtName(req.getCmdtype() + ":" + req.getCmd());
        return 0;//us.insert(m);
    }

    public MsgResponse process(MsgHandler handler) {
        IUserContext context = handler.getUserContext();

        MsgRequest req = handler.req;
        SysUserService sysUserService = new SysUserServiceImpl();
        //用户登录
        if (req.cmd.equals("login")) {

            retcode = 0;
            retmsg = "登录成功";
            msgBody = "{}";
            Integer loginType = req.msgBody.getInteger("loginType");
            loginType = loginType == null ? 1 : loginType;
            if (loginType == 1) {//账号密码登录
                String userName = req.msgBody.getString("userName");
                String password = req.msgBody.getString("password");
                String ip = req.msgBody.getString("ip");

                Map<String, Object> map = sysUserService.checkUserByUserName(userName, password, ip);
                int userId = Integer.parseInt(map.get("userId").toString());

                String token = map.get("token").toString();
                LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);
                req.setToken(token);
                loginSso.getLoginSsoJson().setToken(token);
                msgBody = "{\"list\":[" + loginSso.getLoginSsoJson() + "]}";
                return handler.buildMsg(retcode, retmsg, msgBody);


            } else {//手机号登录
                String mobile = req.msgBody.getString("mobile");
                String phoneCode = req.msgBody.getString("phoneCode");
                String ip = req.msgBody.getString("ip");

                Map<String, Object> map = sysUserService.checkUserByMobile(mobile,ip);
                int userId = Integer.parseInt(map.get("userId").toString());

                Api_KeyModel keyModel = SafeContext.genApiKey(userId);
                insertUserLog(handler.req, userId);
                String token = map.get("token").toString();
                req.setToken(token);
                LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

                msgBody = "{\"list\":[" + loginSso.getLoginSsoJson() + "]}";
                return handler.buildMsg( retcode, retmsg,msgBody );


            }


        }

        //查询用户列表列表
        else if (req.cmd.equals("getUserList")) {
            String userName = req.msgBody.getString("userName");
            Map params = new HashMap();
            params.put("userName", userName);
            List<SysUserModel> userList = sysUserService.getSysUserList(params);
            msgBody="{\"list\":"+ JSON.toJSON(userList).toString()+"}";

        }

        //添加用户
        else if(req.cmd.equals("addUserInfo")){
            SysUserModel userModel = req.msgBody.toJavaObject(req.msgBody,SysUserModel.class);
//            UserIdService userIdService = new UserIdServiceImpl();
//            User_IdModel userIdModel = new User_IdModel();
//            userIdModel.setUuId(KunlongUtils.getUUID(true));

            //新增ytb_log库的user_id信息
            userModel.setPassword(DigestUtils.md5Hex(default_pwd));
            //userIdService.addUserId(userIdModel);

            //新增用户信息
            //userModel.setUserId(userIdModel.getId());
            //userModel.setUserId(KunlongUtils.fsGetUID());
            sysUserService.addUserInfo(userModel);
            userModel.setCreateBy(context.getLoginSso().getUserId());
            if(userModel.getUserId() == 0){
                retmsg = "失败";
                retcode = 1;
            }
            msgBody="{\"userId\":"+ userModel.getUserId()+"}";

        }
        //修改用户信息
        else if(req.cmd.equals("updateUserInfo")){
            SysUserModel userModel = req.msgBody.toJavaObject(req.msgBody,SysUserModel.class);
            sysUserService.updateSysUserInfo(userModel);
        }
        //删除用户信息
        else if(req.cmd.equals("deleteUserById")){
            int userId = Integer.parseInt(req.msgBody.getString("userId"));
            sysUserService.deleteUserbyId(userId);

        }

        //启用、禁用账户
        else if(req.cmd.equals("setStatus")){
            Map<String,Object> map = new HashMap<>();
            int userId = req.msgBody.getInteger("userId");
            int status = req.msgBody.getInteger("status");
            map.put("userId",userId);
            map.put("status",status);
            sysUserService.updateStatus(map);

        }

        //用户修改密码
        else if(req.cmd.equals("updatePassword")){

            String newPwd = req.msgBody.getString("newPwd");
            String oldPwd = req.msgBody.getString("oldPwd");

            sysUserService.updatePassword( DigestUtils.md5Hex(newPwd),getLoginSsoJson(req.token).getUserId(),oldPwd);

        }
        else{
            throw new KunlongError(KunlongError.CODE_INVALID_REST);
        }

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    private LoginSsoJson getLoginSsoJson(String token){
        LoginSso loginSso = SafeContext.getLog_ssoAndApiKey(token);

        LoginSsoJson loginSsoJson = JSONObject.parseObject(loginSso.getJson().toString(), LoginSsoJson.class);
        return loginSsoJson;
    }

}


