package com.kunlong.platform.controller.web.context;


import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.context.RestMessage.MsgHandler;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.IRestProcess;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 后台元数据字典模块的Rest类
 * Package: ytb.manager.sysmetadata.rest
 * Author: cch/leijiming
 * Date: Created in 2018/8/23 16:50
 */
@RestController
@RequestMapping("/auth")
public final class AuthNoController implements IRestProcess {


    @RequestMapping("/context/getPicCode")
    public void getPicCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        MsgHandler msgHandler = new MsgHandler();
        try {

            String ip= KunlongUtils.getIpAddr(request);
            AppKlongContext.getLoginContext().genPicCode(ip, response);
            return;
        } catch (KunlongError e) {
            msgHandler.buildMsg( e );
        } catch (Exception e) {
            msgHandler.buildMsg(e);
        }

        response.setCharacterEncoding(UTF_8);
        response.setContentType("application/json");
        response.getWriter().write(msgHandler.resp.toJSONString());
    }


    @Override
    public MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new MsgResponse();
    }
}

