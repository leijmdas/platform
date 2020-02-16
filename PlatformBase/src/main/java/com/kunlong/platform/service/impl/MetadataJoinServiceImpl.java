package com.kunlong.platform.service.impl;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.dubbo.api.model.SelectSqlDTO;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataFieldModelService;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.utils.JsonResult;
import com.kunlong.platform.utils.SqlSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class MetadataJoinServiceImpl implements MetadataJoinService {
    @Autowired
    SqlSessionUtil sqlSessionUtil;

    @Autowired
    MetadataDictModelService metadataDictModelService;

    @Autowired
    MetadataFieldModelService metadataFieldModelService;

    List<MetadataFieldModel> findAllByMetadataId(Integer metadataId,Integer limit) {
        MetadataFieldModel.QueryParam queryParam = new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(metadataId);
        queryParam.setLimit(limit);
        return metadataFieldModelService.findByQueryParam(queryParam);

    }

    @Transactional
    void deleteAllByMetadataId(List<MetadataFieldModel> models)     {

        if (models != null) {
            for (MetadataFieldModel model : models) {
                metadataFieldModelService.deleteById(model.getFieldId());
            }
        }
    }

    public JsonResult<Integer> deleteAllByMetadataId(Integer metadataId)     {

        List<MetadataFieldModel> models = findAllByMetadataId(metadataId,-1);
        deleteAllByMetadataId(models);

        return JsonResult.success();
    }
    public Boolean checkExistsFieldByMetadataId(Integer metadataId){
        List<MetadataFieldModel> models = findAllByMetadataId(metadataId,1);
        return models!=null&&models.size()>0;
    }

    public Integer copyMaster(Integer metadataId) {

        MetadataDictModel metadataDict = metadataDictModelService.findById(metadataId);
        if (metadataDict == null) {
            return -1;
        }
        metadataDict.setMetadataAlias(metadataDict.getMetadataAlias() + System.currentTimeMillis());
        metadataDict.setMetadataName(metadataDict.getMetadataName() + System.currentTimeMillis());
        metadataDict.setMetadataId(null);
        metadataDict.setMetadataAutocreate(true);
        metadataDictModelService.save(metadataDict);

        List<MetadataFieldModel> models = findAllByMetadataId(metadataId,-1);

        for (MetadataFieldModel metadataField : models) {
            metadataField.setMetadataId(metadataDict.getMetadataId());
            metadataField.setFieldId(null);
            metadataFieldModelService.save(metadataField);
        }
        return metadataDict.getMetadataId();
    }

    public PageResult<Map<String, Object>> checkDict(MetadataQueryDTO metadataQuery) {

        StringBuilder sql = new StringBuilder();
        sql.append("call spCheckMetadata");
        sql.append("(").append(metadataQuery.getParam().getMetadataId()).append(")");
        List<Map<String, Object>> rs = sqlSessionUtil.selectList(sql.toString());
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setData(rs);
        pageResult.setTotal(rs.size());
        return pageResult;
    }

    public PageResult<Map<String, Object>> selectTable(MetadataQueryDTO metadataQuery) {
        if (metadataQuery.getParam() == null) {
            metadataQuery.setParam(new SelectSqlDTO());

        }

        if (metadataQuery.getParam().getMetadataId() != null) {
            MetadataDictModel metadataDict = metadataDictModelService.findById(metadataQuery.getParam().getMetadataId());
            if (metadataDict == null) {
                throw new RuntimeException("表不存在！");
            }
            metadataQuery.getParam().setDb(metadataDict.getMetadataDb());
            metadataQuery.getParam().setTable(metadataDict.getMetadataName());
        }

        List<Map<String, Object>> rs = sqlSessionUtil.selectTable(metadataQuery);
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setData(rs);
        long count = sqlSessionUtil.countTable(metadataQuery);
        pageResult.setTotal(count);
        return pageResult;
    }

    public JsonResult<Integer> dropDbTable(Integer metadataId) {
        MetadataDictModel metadataDict = metadataDictModelService.findById(metadataId);
        if (metadataDict == null) {
            return JsonResult.failure(-1, "字典未定义!");
        }
        if (!checkTableExists(metadataDict)) {
            return JsonResult.failure(-2, "表不存在!");
        }
        if (!metadataDict.getMetadataAutocreate()) {

            return JsonResult.failure(-11, "创建标识为FALSE（metadataAutocreate）!");
        }
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select 1 from ");
        sql.append(metadataDict.getMetadataDb()).append(".").append(metadataDict.getMetadataName());
        sql.append(" limit 1");
        List<Map<String, Object>> list = sqlSessionUtil.selectList(sql.toString());
        //有记录不能删除的
        if (list.size() > 0) {
            return JsonResult.failure(-121, "有记录不能删除的!");

        }
        sql.delete(0, sql.length());
        sql.append(" drop table ");
        sql.append(metadataDict.getMetadataDb()).append(".").append(metadataDict.getMetadataName());
        sqlSessionUtil.update(sql);
        return JsonResult.success(0);

    }

    //SELECT  *  FROM information_schema.TABLES WHERE  TABLE_SCHEMA='ytb_manager'
    Boolean checkTableExists( MetadataDictModel model ) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select 1 from information_schema.TABLES");
        sql.append(" where  TABLE_SCHEMA='").append(model.getMetadataDb()).append("'");
        sql.append(" and table_name='").append(model.getMetadataName()).append("'");
        List list = sqlSessionUtil.selectList(sql.toString());
        return list != null && list.size() > 0;

    }

    public JsonResult<Integer> makeDbTable(Integer metadataId) {

        if (metadataId == null) {
            return JsonResult.failure(-1, "metadataId is null!");
        }

        MetadataDictModel metadataDict = metadataDictModelService.findById(metadataId);
        if (metadataDict == null) {
            return JsonResult.failure(-10, "字典未定义!");
        }

        if (!metadataDict.getMetadataAutocreate()) {

            return JsonResult.failure(-11, "metadataAutocreate=false!");
        }
        if (checkTableExists(metadataDict)) {
            return JsonResult.failure(-20, "表已经存在!");
        }

        return firstMake(metadataId, metadataDict);
    }


    JsonResult<Integer> firstMake(Integer metadataId, MetadataDictModel metadataDict){
        MetadataFieldModel.QueryParam queryParam=new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(metadataId);
        queryParam.setSortBys("field_pk|desc,field_order|asc");
        queryParam.setLimit(-1);
        List<MetadataFieldModel> fieldList = metadataFieldModelService.findByQueryParam(queryParam);

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
                //String[]  s= StringUtils.split(fieldList.get(i).getFieldSize(),",",true);
                sql.append("(").append(fieldList.get(i).getFieldSize()).append(",4) ");
            } else if (isPCT(fieldList.get(i).getFieldType())) {
                sql.append("(12,4) ");
            } else if (isMoney(fieldList.get(i).getFieldType())) {
                sql.append("(12,2) ");
            }


            if (fieldList.get(i).getFieldPk() != null && fieldList.get(i).getFieldPk()) {
                if (fieldList.get(i).getFieldPk() == true) {
                    sql.append(" primary key NOT NULL ");
                }
                if (fieldList.get(i).getFieldAuto() == true) {
                    sql.append(" AUTO_INCREMENT ");
                }
            } else {
                if (fieldList.get(i).getFieldIsNull()) {
                    sql.append("  NULL ");
                } else {
                    sql.append("  NOT NULL ");
                }

                if (!isBlobText(fieldList.get(i).getFieldType()) && fieldList.get(i).getFieldDefault() != null && !fieldList.get(i).getFieldDefault().isEmpty()) {
                    sql.append(" DEFAULT ");
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

        sqlSessionUtil.update(sql);
        return JsonResult.success(0);
    }

    boolean isDate(String dtype) {
        return dtype.equals("DATE")  || dtype.equals("TIMESTAMP") || dtype.equalsIgnoreCase("DATETIME");
    }

    boolean isString(String dtype) {
        return dtype.equals("CHAR")   || dtype.equalsIgnoreCase("VARCHAR");
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
}
