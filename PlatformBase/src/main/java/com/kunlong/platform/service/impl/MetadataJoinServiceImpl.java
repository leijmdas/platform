package com.kunlong.platform.service.impl;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.dubbo.api.model.SelectSqlDTO;
import com.kunlong.platform.domain.CheckDictResult;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataFieldModelService;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.service.autowebpage.WebPageUtil;
import com.kunlong.platform.utils.JsonResult;
import com.kunlong.platform.utils.SqlSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class MetadataJoinServiceImpl implements MetadataJoinService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    WebPageUtil webPageUtil;

    @Autowired
    SqlSessionUtil sqlSessionUtil;

    @Autowired
    MetadataDictModelService metadataDictModelService;

    @Autowired
    MetadataFieldModelService metadataFieldModelService;

    public List<MetadataFieldModel> findAllByMetadataId(Integer metadataId, Integer limit) {
        MetadataFieldModel.QueryParam queryParam = new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(metadataId);
        queryParam.setSortBys("fieldOrder|asc");
        queryParam.setLimit(limit);
        return metadataFieldModelService.findByQueryParam(queryParam);

    }

    @Transactional
    public void deleteAllByMetadataId(List<MetadataFieldModel> models) {

        for (MetadataFieldModel model : models) {
            metadataFieldModelService.deleteById(model.getFieldId());
        }

    }

    public JsonResult<Integer> deleteAllByMetadataId(Integer metadataId) {

        List<MetadataFieldModel> models = findAllByMetadataId(metadataId, -1);
        deleteAllByMetadataId(models);

        return JsonResult.success(0);
    }

    public Boolean checkExistsFieldByMetadataId(Integer metadataId) {
        List<MetadataFieldModel> models = findAllByMetadataId(metadataId, 1);
        return models != null && models.size() > 0;
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

        List<MetadataFieldModel> models = findAllByMetadataId(metadataId, -1);

        for (MetadataFieldModel metadataField : models) {
            metadataField.setMetadataId(metadataDict.getMetadataId());
            metadataField.setFieldId(null);
            metadataFieldModelService.save(metadataField);
        }
        return metadataDict.getMetadataId();
    }
    //    MetadataDictModel metadataDict = metadataDictModelService.findById(metadataId);
    //        if (metadataDict == null) {
    //            return new StringBuilder("表不存在！");
    //        }
    //


    public PageResult<CheckDictResult> checkDict(Integer id) {
        StringBuilder sql = new StringBuilder();
        sql.append("call spCheckMetadata").append("(").append(id).append(")");
        List<CheckDictResult> rs = sqlSessionUtil.selectList(sql, CheckDictResult.class);

        PageResult<CheckDictResult> pageResult = new PageResult<>();
        pageResult.setData(rs);
        pageResult.setTotal(rs.size());
        return pageResult;
    }

    public Boolean checkTableExists(Integer id) {
        MetadataDictModel metadataDict = metadataDictModelService.findById(id);
        if (metadataDict == null) {
            return false;
        }
        return checkTableExists(metadataDict);

    }

    public PageResult<Map<String, Object>> selectTable(MetadataQueryDTO metadataQuery) {
        if (metadataQuery.getParam() == null) {
            metadataQuery.setParam(new SelectSqlDTO());

        }

        if (metadataQuery.getParam().getMetadataId() == null) {
            return null;
        }
        MetadataDictModel metadataDict = metadataDictModelService.findById(metadataQuery.getParam().getMetadataId());
        if (metadataDict == null) {
            throw new RuntimeException("表不存在！");
        }
        metadataQuery.getParam().setDb(metadataDict.getMetadataDb());
        metadataQuery.getParam().setTable(metadataDict.getMetadataName());


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
        List<Map<String, Object>> list = sqlSessionUtil.selectList(sql);
        //有记录不能删除的
        if (list.size() > 0) {
            return JsonResult.failure(-12, "有记录不能删除的!");

        }
        sql.delete(0, sql.length());
        sql.append(" drop table ");
        sql.append(metadataDict.getMetadataDb()).append(".").append(metadataDict.getMetadataName());
        sqlSessionUtil.update(sql);
        return JsonResult.success(0);

    }

    //SELECT  *  FROM information_schema.TABLES WHERE  TABLE_SCHEMA='ytb_manager'
    Boolean checkTableExists(MetadataDictModel model) {
        StringBuilder sql = new StringBuilder(128);
        sql.append("select 1 from information_schema.TABLES");
        sql.append(" where  TABLE_SCHEMA='").append(model.getMetadataDb()).append("'");
        sql.append(" and table_name='").append(model.getMetadataName()).append("'");
        List list = sqlSessionUtil.selectList(sql);
        return list.size() > 0;

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


    JsonResult<Integer> firstMake(Integer metadataId, MetadataDictModel metadataDict) {
        MetadataFieldModel.QueryParam queryParam = new MetadataFieldModel.QueryParam();
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
        for (MetadataFieldModel fieldModel : fieldList) {
            sql.append(fieldModel.getFieldName()).append(" ");
            if (fieldModel.isPCT() || fieldModel.isMoney()) {
                sql.append(" DECIMAL ");
            }
            if (fieldModel.isTAGIMAGE() || fieldModel.isMoney()) {
                sql.append(" INT ");
            } else {
                sql.append(fieldModel.getFieldType());
            }

            if (fieldModel.isString()) {
                sql.append("(").append(fieldModel.getFieldSize()).append(") ");
            } else if (fieldModel.isDecimal()) {

                //String[]  s= StringUtils.split(fieldList.get(i).getFieldSize(),",",true);
                sql.append("(").append(fieldModel.getFieldSize()).append(",4) ");

            } else if (fieldModel.isPCT()) {
                sql.append("(12,4) ");
            } else if (fieldModel.isMoney()) {
                sql.append("(12,2) ");
            }


            if (fieldModel.getFieldPk() != null && fieldModel.getFieldPk()) {
                sql.append(" primary key NOT NULL ");
                if (fieldModel.getFieldAuto()) {
                    sql.append(" AUTO_INCREMENT ");
                }
            } else {
                sql.append(fieldModel.getFieldIsNull() ? "  NULL " : "  NOT NULL ");

                if (!fieldModel.isBlobText()
                        && fieldModel.getFieldDefault() != null
                        && !fieldModel.getFieldDefault().isEmpty()) {
                    sql.append(" DEFAULT ");
                    if (fieldModel.isString()) {
                        sql.append("'").append(fieldModel.getFieldDefault()).append("'");
                    } else if (fieldModel.isDate()) {
                        if (fieldModel.getFieldDefault() != null && fieldModel.getFieldDefault().length() >= 10) {
                            sql.append("'").append(fieldModel.getFieldDefault()).append("'");
                        } else {
                            sql.append(fieldModel.getFieldDefault());
                        }
                    } else {
                        sql.append(fieldModel.getFieldDefault());
                    }
                }
            }
            sql.append(" comment '").append(fieldModel.getFieldMemo()).append(" ");
            sql.append(fieldModel.getFieldRemark()).append("' ,");
        }

        sql.deleteCharAt(sql.length() - 1);
        sql.append(")" + " ENGINE=Innodb DEFAULT CHARSET=UTF8 COLLATE UTF8_BIN ");
        sql.append(" comment '").append(metadataDict.getMetadataAlias()).append("'; ");

        sqlSessionUtil.update(sql);
        return JsonResult.success(0);
    }


    public StringBuilder makeWebPage(Integer metadataId) throws IOException {

        return webPageUtil.makeWebPage(this,metadataId);
    }

}
