package com.kunlong.platform.controller.web.metadata;

import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.RestHandler;
import com.kunlong.platform.controller.web.metadata.impl.ConfigCenterRestProcess;
import com.kunlong.platform.controller.web.metadata.impl.MetaDataProcess;
import com.kunlong.platform.model.KunlongError;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台元数据字典模块的Rest类
 * Package: com.kunlong.metadata.sysuser
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@RestController
@RequestMapping("/rest")
@Scope("prototype")
public class MetaDataController extends RestHandler {

    @PostMapping(value = "/sysmetadata", produces = "application/json;charset=UTF-8")
    public String metaDataRest(@RequestBody String data) {
        try {
            parseRequest(data);
            resp = process();
        } catch (KunlongError e) {
            e.printStackTrace();
            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
        } catch (Exception e) {
            e.printStackTrace();
            KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
        }
        return resp.toJSONString();

    }
//
//    @RequestMapping(value = "/configCenter", produces = "application/json;charset=UTF-8")
//    public String configCenter(@RequestBody String data) {
//        try {
//            parseRequest(data);
//            resp = process();
//        } catch (KunlongError e) {
//            e.printStackTrace();
//            resp = buildMsg(e.getRetcode(), e.getMsg(), "{}");
//        } catch (Exception e) {
//            e.printStackTrace();
//            KunlongError ye = new KunlongError(KunlongError.CODE_UNKNOWN_ERROR);
//            resp = buildMsg(ye.getRetcode(), e.getMessage(), "{}");
//        }
//        return resp.toJSONString();
//
//    }

    protected MsgResponse process() {
        if (req.cmdtype.equals("metadata")) {
            return new MetaDataProcess().process(req, this);
        }
//        else if (req.cmdtype.equals("configCenter")) {
//            //return new ConfigCenterRestProcess().process(req, this);
//        }
        throw new KunlongError(KunlongError.CODE_INVALID_REST);

    }

}

