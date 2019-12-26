package com.kunlong.metadata.service.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kunlong.metadata.model.SelectSql;
import com.kunlong.metadata.service.MetadataDictService;
import com.kunlong.metadata.service.SysMetaDataService;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.RestHandler;
import com.kunlong.platform.model.KunlongError;
import com.kunlong.platform.utils.KunlongUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.Map;
@Component
public class TableServer {
    @Autowired
    SysMetaDataService sysMetaDataService ;//= new SysMetaDataServiceImpl();
    @Autowired
    MetadataDictService metadataDictService ;//= new SysMetaDataServiceImpl();

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    void checkTableExists(SelectSql ss){
        String tbl=ss.getTable();
        String [] names=tbl.split("\\.");
        if(names.length>1) {
            if(!metadataDictService.checkTableExists(names[0],names[1])){
                throw new KunlongError(KunlongError.CODE_UNKNOWN_ERROR," 表不存在！");
            }
        }
    }
    public MsgResponse selectByTableByLimitOrder(MsgRequest req, RestHandler handler) {


        SelectSql asd = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(asd);

        List<Map<String, Object>> list = sysMetaDataService.selectByTableLimitOrderBy(asd);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        msgBody = "{\"list\":" + JSONObject.toJSONString(array, SerializerFeature.WriteMapNullValue) + "}";

        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse selectByTableByLimit(MsgRequest req, RestHandler handler) {

        SelectSql asd = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(asd);

        List<Map<String, Object>> list = sysMetaDataService.selectByTableLimit(asd);
        JSONArray array = JSONArray.parseArray(JSON.toJSONString(list));
        msgBody = "{\"list\":" + array.toJSONString() + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse selectByTable(MsgRequest req, RestHandler handler) {


       SelectSql selectSql = JSONObject.parseObject(req.msgBody.toString(), SelectSql.class);
        checkTableExists(selectSql);
        List<Map<String, Object>> list = sysMetaDataService.selectByTable(selectSql);
        msgBody = "{\"list\":" + KunlongUtils.toJSONStringPretty(list) + "}";

        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    public MsgResponse makeTableByDictId(MsgRequest req, RestHandler handler) {

        int metadataId = req.msgBody.getInteger("metadataId");
        return sysMetaDataService.makeTableByDictId(metadataId, req, handler);
    }




}
