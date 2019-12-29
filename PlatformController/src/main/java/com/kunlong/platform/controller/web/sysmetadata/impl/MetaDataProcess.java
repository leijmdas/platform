package com.kunlong.platform.controller.web.sysmetadata.impl;

import com.kunlong.metadata.service.server.DictDataTypeServer;
import com.kunlong.metadata.service.server.MetadataDictServer;
import com.kunlong.metadata.service.server.MetadataFieldServer;
import com.kunlong.metadata.service.server.TableServer;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.RestHandler;
import com.kunlong.platform.model.KunlongError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * Package: com.kunlong.sysmetadata.sysuser.impl
 * Author: ljm
 * Date: Created in 2019/8/23 18:11
 */
@Service
public class MetaDataProcess {
    private static final Logger logger = LoggerFactory.getLogger(MetaDataProcess.class);

    @Resource(name = "dictDataTypeServer")
    DictDataTypeServer dictDataTypeServer;

    @Resource(name = "metadataDictServer")
    MetadataDictServer metadataDictServer;

    @Resource(name = "metadataFieldServer")
    MetadataFieldServer metadataFieldServer;

    @Resource(name = "tableServer")
    TableServer tableServer;

    int retcode = 0;
    String retmsg = "成功";
    String msgBody = null;

    public MsgResponse process(MsgRequest req, RestHandler handler) {
        logger.info("MetaDataProcess req: {}",req);
        retcode = 0;
        retmsg = "成功";
        msgBody = null;
        if (req.cmd.equals("getCachedTableList")) {
            return metadataDictServer.getCachedTableList(req, handler);
        } else if (req.cmd.equals("updateFieldOrder")) {
            return metadataDictServer.updateFieldOrder(req, handler);

        } else if (req.cmd.equals("getDictList")) {
            return metadataDictServer.getDictList(req, handler);

        } else if (req.cmd.equals("checkDict")) {
            return metadataDictServer.checkDict(req, handler);
        }
        //获取元数据子表中的field default value lsit
        else if (req.cmd.equals("getDictFieldsDefaultValueList")) {
            return metadataDictServer.getDictFieldsDefaultValueList(req, handler);
        }//获取主表中的所有记录
        else if (req.cmd.equals("getDictListAndField")) {
            return metadataDictServer.getDictListAndField(req, handler);
        } else if (req.cmd.equals("getDictTableAndField")) {
            return metadataDictServer.getDictTableAndField(req, handler);

        }

        //通过元数据类型查询出所有的记录
        else if (req.cmd.equals("getDictListByType")) {

            return metadataDictServer.getDictListByType(req, handler);

        }
        //
        else if (req.cmd.equals("getDictListAndFieldByType")) {
            return metadataDictServer.getDictListAndFieldByType(req, handler);

        }
        //getDictAndFieldByType
        else if (req.cmd.equals("getDictAndFieldByType")) {

            return metadataDictServer.getDictAndFieldByType(req, handler);

        } else if (req.cmd.equals("getDictMetadataName")) {
            return metadataDictServer.getDictMetadataName(req, handler);


        } else if (req.cmd.equals("getDictAndFieldByMetadataName")) {

            return metadataDictServer.getDictAndFieldByMetadataName(req, handler);

        }

        //通过主表主键查询
        else if (req.cmd.equals("getDictById")) {


            return metadataDictServer.getDictById(req, handler);
        }


        //通过主键删除字段
        else if (req.cmd.equals("deleteDictById")) {
            return metadataDictServer.deleteDictById(req, handler);
        }


        //向dict_datatype表中增加一条数据
        else if (req.cmd.equals("addDictDataType")) {
            return metadataDictServer.addDictDataType(req, handler);
        }

        //根据主键修改dict_datatype表中的数据
        else if (req.cmd.equals("updateDictDataTypeById")) {
            return metadataDictServer.updateDictDataTypeById(req, handler);
        }

        //根据主键删除dict_datatype表的数据
        else if (req.cmd.equals("deleteDictDataTypeById")) {
            return metadataDictServer.deleteDictDataTypeById(req, handler);
        }


        //获取subsys_dict表中的所有数据
        else if (req.cmd.equals("getSubSysDictList")) {
            return metadataDictServer.getSubSysDictList(req, handler);
        }


        /*dict  */
        else if (req.cmd.equals("dictByUpdateByKey")) {
            return metadataDictServer.dictByUpdateByKey(req, handler);
        }

        /*dict  */
        else if (req.cmd.equals("dictByInsertSelective")) {

            return metadataDictServer.dictByInsertSelective(req, handler);

        } else if (req.cmd.equals("copyMaster")) {

            return metadataDictServer.copyMaster(req.getMsgBody().getInteger("metadataId"), handler);

        } else if (req.cmd.equals("dpMaster")) {

            return metadataDictServer.dpMaster(req.getMsgBody().getInteger("metadataId"), handler);

        }
        /*dict  */
        else if (req.cmd.equals("dictDeleteByKey")) {
            return metadataDictServer.dictDeleteByKey(req, handler);


        }
        //dictBySelectByKey
        else if (req.cmd.equals("dictBySelectByKey")) {
            return metadataDictServer.dictBySelectByKey(req, handler);

        }
        //dictByUpdate
        else if (req.cmd.equals("dictByUpdate")) {

            return metadataDictServer.dictByUpdate(req, handler);


        }
        //通过主表主键查询对应的字段信息
        else if (req.cmd.equals("getFieldListByDictId")) {


            return metadataFieldServer.getFieldListByDictId(req, handler);
        }      //通过主键删除字段
        else if (req.cmd.equals("deleteFieldById")) {
            return metadataFieldServer.deleteFieldById(req, handler);
        }
        //fieldByUpdateByKey
        else if (req.cmd.equals("fieldByUpdateByKey")) {

            return metadataFieldServer.fieldByUpdateByKey(req, handler);

        } else if (req.cmd.equals("fieldByInsertSelective")) {

            return metadataFieldServer.fieldByInsertSelective(req, handler);


        } else if (req.cmd.equals("fieldByDeleteByKey")) {
            return metadataFieldServer.fieldByDeleteByKey(req, handler);


        } else if (req.cmd.equals("fieldBySelectByKey")) {

            return metadataFieldServer.fieldBySelectByKey(req, handler);


        } else if (req.cmd.equals("fieldByDeleteByMetadataId")) {

            return metadataFieldServer.fieldByDeleteByMetadataId(req, handler);

        }

        //通过主键id创建数据表
        else if (req.cmd.equals("makeTableByDictId")) {

            return tableServer.makeTableByDictId(req, handler);

        } else if (req.cmd.equals("selectByTableByLimitOrder")) {

            return tableServer.selectByTableByLimitOrder(req, handler);

        } else if (req.cmd.equals("selectByTableByLimit")) {

            return tableServer.selectByTableByLimit(req, handler);

        } else if (req.cmd.equals("selectByTable")) {
            TableServer tableServer = new TableServer();
            return tableServer.selectByTable(req, handler);
        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("getDictDataTypeList")) {

            return dictDataTypeServer.getDictDataTypeList(req, handler);

        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("getDictDataTypeById")) {

            return dictDataTypeServer.getDictDataTypeById(req, handler);

        }
        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("addDictDataType")) {

            return dictDataTypeServer.addDictDataType(req, handler);

        }

        //获取dict_datatype表中的所有数据
        else if (req.cmd.equals("updateDictDataTypeById")) {

            return dictDataTypeServer.updateDictDataTypeById(req, handler);

        } else if (req.cmd.equals("deleteDictDataTypeById")) {
            return dictDataTypeServer.deleteDictDataTypeById(req, handler);
        } else if (req.cmd.equals("selectDatatype")) {
            return dictDataTypeServer.selectDatatype(req, handler);
        }


        throw new KunlongError(KunlongError.CODE_INVALID_REST);


    }


}
