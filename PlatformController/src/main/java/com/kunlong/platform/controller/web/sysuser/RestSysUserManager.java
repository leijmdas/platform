package com.kunlong.platform.controller.web.sysuser;

import com.kunlong.platform.context.RestMessage.MsgHandler;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.IRestProcess;
import com.kunlong.platform.controller.web.sysuser.impl.SysPower;
import com.kunlong.platform.controller.web.sysuser.impl.SysRole;
import com.kunlong.platform.controller.web.sysuser.impl.SysUser;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.service.SafeContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台管理系统的Rest类
 * Package: ytb.manager.sysuser.sysuser
 * Author: ZCS
 * Date: Created in 2018/8/21 13:20
 * Copyright: Copyright (c) 2018
 */

@RestController
@RequestMapping("/rest")
//@Scope("prototype")
public class RestSysUserManager implements IRestProcess {


    @RequestMapping(value = "sysuser", produces = {"Application/json;charset=UTF-8"})
    @ResponseBody
    public String sysUserRest(@RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        //SysUserContext suc = new SysUserContext();
        return new MsgHandler( ).parseRequest(this, data, request, response);
    }


    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) {
        if (handler.req.cmdtype.equals("user") && handler.req.cmd.equals("login")) {

        } else {
            handler.getUserContext().setLoginSso(SafeContext.getLog_ssoAndApiKey(handler.req.token));
            handler.req.msgBody.put("login_userId", handler.getUserContext().getLoginSso().getUserId());
        }
        handler.req.msgBody.put("ip", KunlongUtils.getIpAddr(request));

        if (handler.req.cmdtype.equals("user")) {
            return new SysUser().process(handler);
        } else if (handler.req.cmdtype.equals("role")) {
            return new SysRole().process(handler);
        } else if (handler.req.cmdtype.equals("menu")) {
            return new SysPower().process(handler);
        }

        throw new KunlongError(KunlongError.CODE_INVALID_REST);
    }


}




