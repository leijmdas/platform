package com.kunlong.platform.context.rest;


import com.kunlong.platform.context.RestMessage.MsgHandler;
import com.kunlong.platform.context.RestMessage.MsgResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Package: com.kunlong.sysmetadata.sysuser.impl
 * Author: leijming
 * Date: Created in 2018/10/17 18:11
 */
public interface IRestProcess {
    final static String UTF_8 = "UTF-8";

    MsgResponse process(MsgHandler handler, HttpServletRequest request, HttpServletResponse response) throws Exception;

}