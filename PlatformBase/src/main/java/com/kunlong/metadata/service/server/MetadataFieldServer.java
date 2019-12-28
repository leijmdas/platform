package com.kunlong.metadata.service.server;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kunlong.metadata.model.MetadataField;
import com.kunlong.metadata.model.MetadataFieldExample;
import com.kunlong.metadata.service.MetadataFieldService;
import com.kunlong.metadata.service.SysMetaDataService;
import com.kunlong.metadata.service.impl.MetadataFieldServiceImpl;
import com.kunlong.metadata.service.impl.SysMetaDataServiceImpl;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.RestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MetadataFieldServer {
    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    @Autowired
    MetadataFieldService metadataFieldService; // = new MetadataFieldServiceImpl();
    @Autowired
    SysMetaDataService sysMetaDataService ;

    public MsgResponse getFieldListByDictId(MsgRequest req, RestHandler handler) {

        Integer metadataId = req.msgBody.getInteger("metadataId");
        MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
        metadataFieldExample.createCriteria().andMetadataIdEqualTo(metadataId);
        List<MetadataField> metadataFields = metadataFieldService.selectByExample(metadataFieldExample);
        msgBody = "{\"list\":" + JSONObject.toJSONString(metadataFields, SerializerFeature.WriteMapNullValue) + "}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse deleteFieldById(MsgRequest req, RestHandler handler) {
        //SysMetaDataServiceImpl sysMetaDataService = new SysMetaDataServiceImpl();

        int metaDataFieldId = req.msgBody.getInteger("fieldId");
        sysMetaDataService.deleteFieldById(metaDataFieldId);
        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public MsgResponse fieldByUpdateByKey(MsgRequest req, RestHandler handler) {
        MetadataField metadataDict = JSONObject.parseObject(req.msgBody.toString(), MetadataField.class);
        metadataDict.setFieldName(metadataDict.getFieldName().trim());
        //MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
        int sta = metadataFieldService.updateByPrimaryKey(metadataDict);

        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public MsgResponse fieldByDeleteByMetadataId(MsgRequest req, RestHandler handler) {
        try {
            Integer metadataId = Integer.parseInt(req.msgBody.getString("metadataId"));

            if (metadataId != null) {
                MetadataFieldExample metadataFieldExample = new MetadataFieldExample();

                metadataFieldExample.createCriteria().andMetadataIdEqualTo(metadataId);
                // MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
                int sta = metadataFieldService.deleteByExample(metadataFieldExample);
                if (sta != 1) {
                    retcode = 1;
                    retmsg = "操作失败";
                    msgBody = "{}";
                } else {

                }

            }
        } catch (Exception e) {
            retcode = 1;
            retmsg = "操作失败";
            msgBody = "{}";
        }
        return handler.buildMsg(retcode, retmsg, msgBody);

    }


    public MsgResponse fieldBySelectByKey(MsgRequest req, RestHandler handler) {
        try {
            Integer fieldId = Integer.parseInt(req.msgBody.getString("fieldId"));

            if (fieldId != null) {
                //MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
                MetadataField sta = metadataFieldService.selectByPrimaryKey(fieldId);
                JSONObject array = JSONObject.parseObject(JSON.toJSONString(sta));
                msgBody = "{\"list\":" + array + "}";

            } else {
                retcode = 1;
                retmsg = "操作失败";
                msgBody = "{}";
            }
        } catch (Exception e) {
            retcode = 1;
            retmsg = "操作失败";
            msgBody = e.toString();

        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }




    public MsgResponse fieldByDeleteByKey(MsgRequest req, RestHandler handler) {
        try {
            Integer fieldId = Integer.parseInt(req.msgBody.getString("fieldId"));

            if (fieldId != null) {
                //MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
                int sta = metadataFieldService.deleteByPrimaryKey(fieldId);
                if (sta != 1) {
                    retcode = 1;
                    retmsg = "操作失败 ";
                    msgBody = "{}";
                } else {

                }

            } else {
                retcode = 1;
                retmsg = "操作失败 fieldId 不得为空";
                msgBody = "{}";
            }
        } catch (Exception e) {
            retcode = 1;
            retmsg = "操作失败";
            msgBody = e.toString();
        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }


    public MsgResponse fieldByInsertSelective(MsgRequest req, RestHandler handler) {
        try {
            MetadataField metadataDict = JSONObject.parseObject(req.msgBody.toString(), MetadataField.class);


            if (metadataDict.getMetadataId() != null) {
                if (metadataDict.getFieldDefault() != null) {
                    if (metadataDict.getFieldDefault().equals("")) {
                        metadataDict.setFieldDefault(null);
                    }
                }
                //MetadataFieldServiceImpl metadataFieldService = new MetadataFieldServiceImpl();
                int sta = metadataFieldService.insertSelective(metadataDict);
                if (sta != 1) {
                    retcode = 1;
                    retmsg = "操作失败";
                    msgBody = "{}";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;

        }
        return handler.buildMsg(retcode, retmsg, msgBody);
    }









}
