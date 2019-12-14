package com.kunlong.platform.controller.web.metadata.impl;

import com.alibaba.fastjson.JSONObject;
import com.kunlong.platform.model.KunlongError;


import java.util.List;

/**
 * Package: ytb.manager.metadata.sysuser.impl
 * Author: XZW
 * Date: Created in 2018/8/23 18:11
 */
public class ConfigCenterRestProcess {

//    ConfigDAOService configManagerService = new ConfigDAOService();
//
//    public MsgResponse process(MsgRequest req, RestHandler handler) {
//
//        //handler.buildMsg(0, "success", "{}");
//        if (req.cmd.equals("getDictConfig")) {
//            List<Dict_ConfigModel> lst = configManagerService.getDictConfig();
//            String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
//            return handler.buildMsg(0,"success",  msgBody);
//        } else
//        if (req.cmd.equals("selectDictConfig")) {
//            List<Dict_ConfigModel> lst = configManagerService.selectDictConfig();
//            String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
//            return handler.buildMsg(0,"success",  msgBody);
//        }
//        else if (req.cmd.equals("getDictErrorCode")) {
//            //List<Dict_ErrorCode> lst = configManagerService.getDictErrorCode();
//            //String msgBody = "{\"list\":" + JSONObject.toJSONString(lst) + "}";
//            return handler.buildMsg(0,"success",  msgBody);
//        }
//
//        throw new KunlongError(KunlongError.CODE_INVALID_REST);
//
//
//    }


}
