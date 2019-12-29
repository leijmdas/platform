package com.kunlong.metadata.service.impl;

import com.kunlong.metadata.dao.*;
import com.kunlong.metadata.model.*;
import com.kunlong.metadata.service.MetadataDictService;
import com.kunlong.metadata.service.MetadataFieldService;
import com.kunlong.metadata.service.SysMetaDataService;
import com.kunlong.mybatis.KunlongSql;
import com.kunlong.platform.context.RestMessage.MsgRequest;
import com.kunlong.platform.context.RestMessage.MsgResponse;
import com.kunlong.platform.context.rest.RestHandler;
import com.kunlong.platform.dao.SubsysDictMapper;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.model.KunlongError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Package: com.kunlong.sysmetadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@Service
public class SysMetaDataServiceImpl implements SysMetaDataService {
    private static final Logger logger = LoggerFactory.getLogger(SysMetaDataServiceImpl.class);

    @Autowired
    MetadataDictService metadataDictService;
    @Autowired
    MetadataFieldService metadataFieldService;

    @Autowired
    SysDictDataTypeMapper sysDictDataTypeDao;
    @Autowired
    SubsysDictMapper subsysDictMapper;
    @Autowired
    SysMetaDataDictMapper sysMetaDataDictDao;
    @Autowired
    MetadataDictMapper metadataDictMapper;
    @Autowired
    MetadataFieldMapper sysMetaDataFieldDao;
    @Autowired
    SysMetaDataFieldMapper sysMetaDataFieldMapper;

    /*
            @Override
            public List<Sys_MetaDataDictModel> getDictList() {
                SqlSession sq = MyBatisUtil.getSession();
                List<Sys_MetaDataDictModel> list;
                try {
                    SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
                    list = sysMetaDataDictDao.getDictList();
                } finally {
                    sq.close();
                }
                return list;
            }*/
/*
    @Override
    public List<Sys_MetaDataDictModel> getDictListByType(int metaDataType) {
        SqlSession sq = MyBatisUtil.getSession();
        List<Sys_MetaDataDictModel> list;
        try {
            SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
            list = sysMetaDataDictDao.getDictListByType(metaDataType);
        } finally {
            sq.close();
        }
        return list;
    }*/
/*
    @Override
    public Sys_MetaDataDictModel getDictById(int metaDataId) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);
        Sys_MetaDataDictModel sysMetaDataDictModel = sysMetaDataDictDao.getDictById(metaDataId);
        sq.close();
        return sysMetaDataDictModel;
    }*/
/*
    @Override
    public void updateDictById(Sys_MetaDataDictModel sysMetaDataDictModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);

        try {
            sysMetaDataDictDao.updateDictById(sysMetaDataDictModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }*/
/*
    @Override
    public void addDict(Sys_MetaDataDictModel sysMetaDataDictModel) {
        SqlSession sq = MyBatisUtil.getSession();
        SysMetaDataDictMapper sysMetaDataDictDao = sq.getMapper(SysMetaDataDictMapper.class);

        try {
            sysMetaDataDictDao.addDict(sysMetaDataDictModel);
            sq.commit();
        } finally {
            sq.close();
        }
    }
*/
    @Override
    public void deleteDictById(int metaDataId) {

        sysMetaDataDictDao.deleteDictById(metaDataId);

    }

    public MetadataField selectByPrimaryKey(Integer metaDataId) {
        return sysMetaDataFieldDao.selectByPrimaryKey(metaDataId);

    }


    public List<MetadataDict> selectByExample(MetadataDictExample getDictMetadataName) {
        return metadataDictMapper.selectByExample(getDictMetadataName);

    }


    @Override
    public void deleteFieldById(int fieldId) {

        sysMetaDataFieldMapper.deleteFieldById(fieldId);

    }


    @Override
    public List<Sys_DictDataTypeModel> getDictDataTypeList() {
        return sysDictDataTypeDao.getDictDataTypeList();
    }

    @Override
    public void addDictDataType(Sys_DictDataTypeModel sysDictDataTypeModel) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String createTime = df.format(now);
        sysDictDataTypeModel.setCreateTime(createTime);
        sysDictDataTypeDao.addDictDataType(sysDictDataTypeModel);

    }

    @Override
    public void updateDictDataTypeById(Sys_DictDataTypeModel sysDictDataTypeModel) {

        sysDictDataTypeDao.updateDictDataTypeById(sysDictDataTypeModel);

    }

    @Override
    public void deleteDictDataTypeById(int dataInnerId) {

        sysDictDataTypeDao.deleteDictDataTypeById(dataInnerId);

    }

    @Override
    public List<Map<String, Object>> getTree(int typeId) {
        return sysDictDataTypeDao.getTree(typeId);
    }


    @Override
    public int getTotalCount() {

        return sysMetaDataDictDao.getTotalCount();
    }

    boolean isDate(String dtype) {
        return dtype.equals("DATE")
                || dtype.equals("TIMESTAMP") || dtype.equalsIgnoreCase("DATETIME");
    }

    boolean isString(String dtype) {
        return dtype.equals("CHAR")
                || dtype.equalsIgnoreCase("VARCHAR");
    }

    boolean isDecimal(String dtype) {
        return dtype.equals("DECIMAL");

    }

    boolean isTAGIMAGE(String dtype) {
        return dtype.equals("TAGIMAGE");

    }


    boolean isPCT(String dtype) {
        return dtype.equals("PCT");

    }

    boolean isMoney(String dtype) {
        return dtype.equals("MONEY");

    }

    boolean isBlobText(String dt) {
        return dt.equals("BLOB")
                || dt.equals("MEDIUMBLOB")
                || dt.equals("TEXT");

    }

    @Override
    public MsgResponse makeTableByDictId(Integer metadataId, MsgRequest req, RestHandler handler) {
        int retcode = 0;
        String retmsg = "成功";
        String msgBody = "";

        if (metadataId == null) {
            throw new KunlongError(KunlongError.CODE_PARAMETER_IS_WRONG);
        }

        MetadataDict metadataDict = metadataDictService.selectByPrimaryKey(metadataId);
        if (metadataDict == null) {
            throw new KunlongError(KunlongError.CODE_DEFINE_ERROR, "字典未定义");
        }



        MetadataFieldExample metadataFieldExampleKey = new MetadataFieldExample();
        MetadataFieldExample.Criteria fieldExampleCriteriaKey = metadataFieldExampleKey.createCriteria();
        fieldExampleCriteriaKey.andFieldPkEqualTo(true);
        fieldExampleCriteriaKey.andMetadataIdEqualTo(metadataId);
        List<MetadataField> fieldListKey = metadataFieldService.selectByExample(metadataFieldExampleKey);
        MetadataFieldExample metadataFieldExample = new MetadataFieldExample();
        MetadataFieldExample.Criteria fieldExampleCriteria = metadataFieldExample.createCriteria();
        fieldExampleCriteria.andMetadataIdEqualTo(metadataId);
        metadataFieldExample.setOrderByClause("field_pk desc,field_order asc");
        List<MetadataField> fieldList = metadataFieldService.selectByExample(metadataFieldExample);

        //构造创建表的sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("create table if not exists ");
        sql.append(metadataDict.getMetadataDb()).append(".").append(metadataDict.getMetadataName());
        sql.append("(");
        for (int i = 0; i < fieldList.size(); i++) {
            sql.append(fieldList.get(i).getFieldName()).append(" ");
            if (isPCT(fieldList.get(i).getFieldType())
                    || isMoney(fieldList.get(i).getFieldType())) {
                sql.append(" DECIMAL ");
            }
            if (isTAGIMAGE(fieldList.get(i).getFieldType())
                    || isMoney(fieldList.get(i).getFieldType())) {
                sql.append(" INT ");
            } else {
                sql.append(fieldList.get(i).getFieldType());
            }

            if (isString(fieldList.get(i).getFieldType())) {
                sql.append("(").append(fieldList.get(i).getFieldSize()).append(") ");
            } else if (isDecimal(fieldList.get(i).getFieldType())) {
                sql.append("(").append(fieldList.get(i).getFieldSize()).append(",2) ");
            } else if (isPCT(fieldList.get(i).getFieldType())) {
                sql.append("(8,4) ");
            } else if (isMoney(fieldList.get(i).getFieldType())) {
                sql.append("(12,2) ");
            }


            if (fieldList.get(i).getFieldPk() != null && fieldList.get(i).getFieldPk()) {
                if (fieldList.get(i).getFieldPk() == true) {
                    sql.append(" primary key NOT NULL ");
                }
                if (fieldList.get(i).getFieldAuto() == true) {
                    sql.append("AUTO_INCREMENT ");
                }
            } else {
                if (fieldList.get(i).getFieldIsnull()) {
                    sql.append("  NULL ");
                } else {
                    sql.append("  NOT NULL ");
                }

                if (!isBlobText(fieldList.get(i).getFieldType()) && fieldList.get(i).getFieldDefault() != null && !fieldList.get(i).getFieldDefault().isEmpty()) {
                    sql.append(" DEFAULT   ");
                    if (isString(fieldList.get(i).getFieldType())) {
                        sql.append("'").append(fieldList.get(i).getFieldDefault()).append("'");
                    } else if (isDate(fieldList.get(i).getFieldType())) {
                        if (fieldList.get(i).getFieldDefault() != null
                                && fieldList.get(i).getFieldDefault().length() >= 10) {
                            sql.append("'").append(fieldList.get(i).getFieldDefault()).append("'");
                        } else {
                            sql.append(fieldList.get(i).getFieldDefault());

                        }
                    } else {
                        sql.append(fieldList.get(i).getFieldDefault());
                    }
                }
            }
            sql.append(" comment '").append(fieldList.get(i).getFieldMemo()).append(" ").append(fieldList.get(i).getFieldRemark()).append("' ,");
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")" + " ENGINE=Innodb DEFAULT CHARSET=UTF8 COLLATE UTF8_BIN ");
        sql.append(" comment '").append(metadataDict.getMetadataAlias()).append("'; ");

        logger.info("sql ",sql);
        System.out.println(sql.toString());

        if (metadataDictService.checkTableExists(metadataDict.getMetadataDb(), metadataDict.getMetadataName())) {
            throw new KunlongError(KunlongError.CODE_DEFINE_ERROR, " 表已经存在！");
        }
        KunlongSql.update(sql);
        msgBody = "{'sql': 'make Table OK'}";
        return handler.buildMsg(retcode, retmsg, msgBody);
    }

    @Override
    public void deleteFieldsByDictId(int metaDataId) {
        StringBuilder sql = new StringBuilder();
        sql.append("delete from metadata_field where metadata_id =" + metaDataId);
        KunlongSql.delete(sql);

    }

    @Override
    public List<Map<String, Object>> selectByTable(SelectSql selectSql) {

        StringBuilder sql = new StringBuilder(152);
        sql.append(" select * from  ").append(selectSql.getTable());
        if (selectSql.getsWhere() != null && !selectSql.getsWhere().isEmpty()) {
            sql.append(" where  ").append(selectSql.getsWhere());
        }
        if (selectSql.getOrderBy() != null && !selectSql.getOrderBy().isEmpty()) {
            sql.append(" order by ").append(selectSql.getOrderBy()).append(" asc ");
        }
        return KunlongSql.selectList(sql);

    }

    @Override
    public List<Map<String, Object>> selectByTableLimit(SelectSql selectSql) {

        StringBuilder sql = new StringBuilder(256);
        sql.append(" select * from ").append(selectSql.getTable());
        if (selectSql.getsWhere() != null) {
            sql.append(" where ").append(selectSql.getsWhere());
        }
        sql.append("  limit ").append(selectSql.getLimitFirstIndex());
        sql.append(",").append(selectSql.getLimitpageSize());
        List<Map<String, Object>> data = KunlongSql.selectList(sql);
        return data;


    }

    @Override
    public List<Map<String, Object>> selectByTableLimitOrderBy(SelectSql selectSql) {

        StringBuilder sql = new StringBuilder();
        sql.append("select * from ").append(selectSql.getTable());
        if (selectSql.getsWhere() != null) {
            sql.append(" where ").append(selectSql.getsWhere());
        }
        if (!selectSql.buildOrderby().isEmpty()) {
            sql.append(" order by ").append(selectSql.buildOrderby());
        }
        sql.append("  limit ").append(selectSql.getLimitFirstIndex());
        sql.append(",").append(selectSql.getLimitpageSize());

        return KunlongSql.selectList(sql);


    }


    public List<SubsysDict> getSubSysDictList() {

        return subsysDictMapper.findByQueryParam(new com.kunlong.platform.domain.SubsysDict.QueryParam());


    }


}
