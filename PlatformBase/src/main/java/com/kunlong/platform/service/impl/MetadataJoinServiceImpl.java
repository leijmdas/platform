package com.kunlong.platform.service.impl;

import app.support.query.PageResult;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.dubbo.api.model.SelectSqlDTO;
import com.kunlong.platform.config.datasource.PfTransactional;
import com.kunlong.platform.domain.*;
import com.kunlong.platform.service.*;
import com.kunlong.platform.service.autowebpage.WebPageUtil;
import com.kunlong.platform.support.service.AuthService;
import com.kunlong.platform.utils.JsonResult;
import com.kunlong.platform.utils.SqlSessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MetadataJoinServiceImpl implements MetadataJoinService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static ExecutorService EXECUTORS = Executors.newCachedThreadPool();

    @Autowired
    SubsysDictService subsysDictService;

    @Autowired
    WebPageUtil webPageUtil;

    @Autowired
    SqlSessionUtil sqlSessionUtil;

    @Autowired
    MetadataDictModelService metadataDictModelService;

    @Autowired
    MetadataFieldModelService metadataFieldModelService;

    public List<MetadataDictModel> exportDictTables(String metadatIds) {
        List<MetadataDictModel> dictModels = new ArrayList<>();
        String[] ids = metadatIds.split(",");
        for (String id : ids) {
            MetadataDictModel dictModel = exportDictTable(Integer.parseInt(id));
            dictModels.add(dictModel);
        }
        return dictModels;
    }

    @PfTransactional
    public MetadataDictModel importDictTables(byte[] bytes) throws UnsupportedEncodingException {

        List<MetadataDictModel> models = JSON.parseObject(new String(bytes, "UTF-8"),
                new TypeReference<List<MetadataDictModel>>() {
                });
        for (MetadataDictModel model : models) {
            importDictTable(model);
        }
        return models.get(0);
    }

    @PfTransactional
    public MetadataDictModel importDictTable(MetadataDictModel model) throws UnsupportedEncodingException {
        if (model == null) {
            return model;
        }
        model.setMetadataId(null);
        metadataDictModelService.save(model);
        for (MetadataFieldModel fieldModel : model.getMetadataFieldModels()) {
            fieldModel.setFieldId(null);
            fieldModel.setMetadataId(model.getMetadataId());
            metadataFieldModelService.save(fieldModel);
        }
        return model;
    }

    public MetadataDictModel importDictTable(byte[] bytes) throws UnsupportedEncodingException {
        String s = new String(bytes, "UTF-8").trim();
        if(s.substring(0,1).equals("{")){
            MetadataDictModel model = JSON.parseObject(s, MetadataDictModel.class);
            importDictTable(model);
            return model;
        }

        return null;
    }


    public MetadataDictModel exportDictTable(Integer metadataId) {
        MetadataDictModel dictModel = metadataDictModelService.findById(metadataId);
        if (dictModel == null) {
           return dictModel;
        }

        List<MetadataFieldModel> fieldModels = findAllByMetadataId(dictModel.getMetadataId(), -1);
        dictModel.setMetadataFieldModels(fieldModels);
        return dictModel;
    }

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

        MetadataDictModel dictModel = metadataDictModelService.findById(metadataId);
        if (dictModel == null) {
            return -1;
        }
        dictModel.setMetadataAlias(dictModel.getMetadataAlias() + System.currentTimeMillis());
        dictModel.setMetadataName(dictModel.getMetadataName() + System.currentTimeMillis());
        dictModel.setMetadataId(null);
        dictModel.setMetadataAutocreate(true);
        metadataDictModelService.save(dictModel);

        List<MetadataFieldModel> models = findAllByMetadataId(metadataId, -1);

        for (MetadataFieldModel metadataField : models) {
            metadataField.setMetadataId(dictModel.getMetadataId());
            metadataField.setFieldId(null);
            metadataFieldModelService.save(metadataField);
        }
        return dictModel.getMetadataId();
    }

    public void doSortMetadataDict(Integer subsysId, String ids) {
        String[] idss = ids.trim().split(",");
        MetadataDictModel dictModel = new MetadataDictModel();
        Integer i = 0;
        for (String id : idss) {
            dictModel.setMetadataId(Integer.valueOf(id));
            dictModel.setMetadataOrder(subsysId * 100 + ++i * 10);
            metadataDictModelService.updateNotNullPropsById(dictModel);
        }

    }
    public Boolean checkExistFieldByMIdAndName(Integer metadataId, String fieldName) {
        MetadataFieldModel.QueryParam queryParam = new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(metadataId);
        queryParam.getParam().setFieldName(fieldName);
        queryParam.setLimit(1);
        List<MetadataFieldModel>  models= metadataFieldModelService.findByQueryParam(queryParam);
        return models.size()>0;

    }

    public List<Integer> dbImportTablesInc(Integer subsysId) {
        List<Integer> result = new ArrayList<>();
        SubsysDict subsysDict = subsysDictService.findById(subsysId);
        if (subsysDict == null) {
            logger.warn("dbImportTables dataModel is null! subsysId:{}",subsysId);
            return  result;
        }

        StringBuilder sql = new StringBuilder(128);
        sql.append(" select * from v_table  where metadata_db='");
        sql.append( subsysDict.getRemark().trim() ).append("'");
        sql.append(" order by metadata_name ");

        List<MetadataDictModel> dictModels = sqlSessionUtil.selectList(sql, MetadataDictModel.class);
        if (dictModels.size() == 0) {
            logger.info("dbImportTables dictModels is empty! sql :{}", sql);
            return result;
        }  else {
            importDbInc(result, subsysId, dictModels);
        }
        return result;
    }
    void importDbInc(List<Integer> result, int subsysId, List<MetadataDictModel> dictModels) {
        int i = 10;
        MetadataDictModel.QueryParam queryParam = new MetadataDictModel.QueryParam();
        queryParam.setParam(new MetadataDictModel());
        queryParam.getParam().setSubsysId(subsysId);
        for (MetadataDictModel dictModel : dictModels) {
            dictModel.setSubsysId(subsysId);
            dictModel.setMetadataType(1);
            dictModel.setMetadataAutocreate(true);
            dictModel.setMetadataSortFields(" ");
            dictModel.setExpTagtableHead(false);
            dictModel.setMetadataOrder(i++);
            dictModel.setMetadataAddDel(false);
            dictModel.setMetadataReadonly(false);
            dictModel.setRefSrc((byte) 0);
            dictModel.setRefObject(" ");
            dictModel.setRefParam(" ");
            if (dictModel.getMetadataAlias().trim().isEmpty()
                    || dictModel.getMetadataAlias().trim().equalsIgnoreCase("VIEW")) {
                dictModel.setMetadataAlias(dictModel.getMetadataName().trim());
            }
            queryParam.getParam().setMetadataName(dictModel.getMetadataName().trim());
            List<MetadataDictModel> metadataDictModels = metadataDictModelService.findByQueryParam(queryParam);
            if (metadataDictModels != null && metadataDictModels.size() > 0) {
                continue;
            }
            metadataDictModelService.save(dictModel);
            result.add(dictModel.getMetadataId());
            dbImportTableFields(dictModel.getMetadataId());
        }
    }
    @Async
    public List<Integer> dbImportTablesAsync(Integer subsysId) {
        List<Integer> result = new ArrayList<>();
        SubsysDict subsysDict = subsysDictService.findById(subsysId);
        if (subsysDict == null) {
            logger.warn("dbImportTables dataModel is null! subsysId:{}",subsysId);
            return  result;
        }

        StringBuilder sql = new StringBuilder(128);
        sql.append(" select * from v_table  where metadata_db='");
        sql.append( subsysDict.getRemark().trim() ).append("'");
        sql.append(" order by metadata_name ");

        List<MetadataDictModel> dictModels = sqlSessionUtil.selectList(sql, MetadataDictModel.class);
        if (dictModels.size() == 0) {
            logger.info("dbImportTables dictModels is empty! sql :{}", sql);
            return result;
        }  else {
            importDb(result, subsysId, dictModels);
        }
        return result;
    }

    // @Transactional(rollbackFor=Exception.class) select * from v_table  where metadata_db='dmp' limit 1 transactionManager="pfTransactionManager"
    // @Transactional(rollbackFor=Exception.class)
    // @PfTransactional()
    // @Async
    public List<Integer> dbImportTables(Integer subsysId) {
        List<Integer> result = new ArrayList<>();
        SubsysDict subsysDict = subsysDictService.findById(subsysId);
        if (subsysDict == null) {
            logger.warn("dbImportTables dataModel is null! subsysId:{}",subsysId);
            return  result;
        }

        StringBuilder sql = new StringBuilder(128);
        sql.append(" select * from v_table  where metadata_db='");
        sql.append( subsysDict.getRemark().trim() ).append("'");
        sql.append(" order by metadata_name ");

        List<MetadataDictModel> dictModels = sqlSessionUtil.selectList(sql, MetadataDictModel.class);
        if (dictModels.size() == 0) {
            logger.info("dbImportTables dictModels is empty! sql :{}", sql);
            return result;
        } else if (dictModels.size() > 100) {
            result.add(-1);//execute submit
            EXECUTORS.execute(new Runnable() {
                @Override
                public void run() {
                    importDb(result, subsysId, dictModels);
                }
            });
        } else {
            importDb(result, subsysId, dictModels);

        }
        return result;
    }

    void importDb(List<Integer> result, int subsysId, List<MetadataDictModel> dictModels) {
        int i = 10;
        MetadataDictModel.QueryParam queryParam = new MetadataDictModel.QueryParam();
        queryParam.setParam(new MetadataDictModel());
        queryParam.getParam().setSubsysId(subsysId);
        for (MetadataDictModel dictModel : dictModels) {
            dictModel.setSubsysId(subsysId);
            dictModel.setMetadataType(1);
            dictModel.setMetadataAutocreate(true);
            dictModel.setMetadataSortFields(" ");
            dictModel.setExpTagtableHead(false);
            dictModel.setMetadataOrder(i++);
            dictModel.setMetadataAddDel(false);
            dictModel.setMetadataReadonly(false);
            dictModel.setRefSrc((byte) 0);
            dictModel.setRefObject(" ");
            dictModel.setRefParam(" ");
            if (dictModel.getMetadataAlias().trim().isEmpty()
                    || dictModel.getMetadataAlias().trim().equalsIgnoreCase("VIEW")) {
                dictModel.setMetadataAlias(dictModel.getMetadataName().trim());
            }
            queryParam.getParam().setMetadataName(dictModel.getMetadataName().trim());
            List<MetadataDictModel> metadataDictModels = metadataDictModelService.findByQueryParam(queryParam);
            if (metadataDictModels != null && metadataDictModels.size() > 0) {
                dictModel.setMetadataId(metadataDictModels.get(0).getMetadataId());
                if (dictModels.size() <= 100) {
                    metadataDictModelService.update(dictModel);
                }
            } else {
                metadataDictModelService.save(dictModel);
            }

            result.add(dictModel.getMetadataId());
            dbImportTableFields(dictModel.getMetadataId());
        }
    }
    // select * from v_col where db_name="dongxw" and table_name='bom'
    //@Transactional(transactionManager="pfTransactionManager",rollbackFor=Exception.class)
    @Async
    @PfTransactional()
    public int dbImportTableFields(Integer metadataId) {
        MetadataDictModel dictModel = metadataDictModelService.findById(metadataId);
        if (dictModel == null) {
            return -1;
        }
        StringBuilder sql = new StringBuilder(128);
        sql.append(" select * from v_col ");
        sql.append(" where db_name='").append(dictModel.getMetadataDb()).append("'");
        sql.append(" and table_name='").append(dictModel.getMetadataName()).append("'");
        sql.append(" order by field_order ");

        List<MetadataFieldModel> fieldModels = sqlSessionUtil.selectList(sql, MetadataFieldModel.class);
        if(fieldModels.size()==0){
            logger.info("dbImportTableFields sql :{}",sql);
            return -1;
        }
        logger.info("dbImportTableFields fieldModels:{}", fieldModels);
        for (MetadataFieldModel model : fieldModels) {
            if (model.getFieldDefault() == null) {
                model.setFieldDefault("");
            }
            model.setMetadataId(dictModel.getMetadataId());
            model.setFieldVisible(true);
            model.setFieldDisplaysize(100);
            model.setFieldSize(11);
            model.setFieldName(model.getFieldName().trim());
            model.setFieldMemo(model.getFieldMemo().trim());
            model.setFieldType(model.getFieldType().toUpperCase().trim());
            if(model.getFieldMemo().trim().isEmpty()){
                model.setFieldMemo(model.getFieldName());
            }
            //field_default
            model.setRefObject(" ");
            model.setRefParameter(" ");
            model.setRefField(" ");
            model.setFieldReadonly(false);
            model.setRefDisplayID(" ");
            model.setRefFilter(" ");
            model.setFieldIscal(false);
            //field_remark field_isNull
            model.setRefTable(" ");
            model.setFieldFormat(" ");
            model.setDisplayColor(" ");
            model.setFieldComponent(0);
            model.setRefPool(" ");
            model.setFieldDecimal(0);
            model.setFieldMin(" ");
            model.setFieldMax(" ");
            model.setFieldSrc(0);

            String[] w = model.getFieldRemark().trim().split("\\(|\\)");

            if (w.length > 1) {
                String[] fieldSize = w[1].split(",");
                model.setFieldSize(Integer.parseInt(fieldSize[0]));
                if (fieldSize.length > 1) {
                     model.setFieldDecimal(Integer.parseInt(fieldSize[1]));
                }
            } else {
                model.setFieldSize(0);
            }
            logger.info("dbImportTableFields model :{}", model);
            if(checkExistFieldByMIdAndName(metadataId,model.getFieldName()))
            {
                logger.warn("dbImportTableFields checkExistFieldByMIdAndName metadataId:{}, getFieldName:{}",metadataId,model.getFieldName());
            }else {
                metadataFieldModelService.save(model);
            }
        }
        return 0;
    }

    public void doSortMetadataField(String ids) {
        String[] idss = ids.trim().split(",");
        if (idss.length == 0) {
            return;
        }
        MetadataFieldModel fieldModel = metadataFieldModelService.findById(Integer.parseInt(idss[0]));
        MetadataDictModel dictModel = metadataDictModelService.findById(fieldModel.getMetadataId());
        Integer subsysId = 0;
        if (dictModel != null) {
            subsysId = dictModel.getSubsysId();
        }
        short i = 0;
        for (String id : idss) {
            fieldModel = new MetadataFieldModel();
            fieldModel.setFieldId(Integer.valueOf(id));
            fieldModel.setFieldOrder(  subsysId * 100 + ++i * 10 );
            metadataFieldModelService.updateNotNullPropsById(fieldModel);
        }

    }

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
        queryParam.setSortBys("fieldPk|desc,fieldOrder|asc");
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
                sql.append("(").append(fieldModel.getFieldSize());
                sql.append(",").append(fieldModel.getFieldDecimal()).append(") ");

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

                if (!fieldModel.isBlobText() && !fieldModel.getFieldIsNull()
                        && fieldModel.getFieldDefault() != null
                        && !fieldModel.getFieldDefault().isEmpty()) {
                    sql.append(" DEFAULT ");
                    if (fieldModel.isString()) {
                        sql.append("'").append(fieldModel.getFieldDefault()).append("'");
                    } else if (fieldModel.isDate()) {
                        if (fieldModel.getFieldDefault() != null
                                && fieldModel.getFieldDefault().length() >= 10
                                && !"CURRENT_TIMESTAMP".equals(fieldModel.getFieldDefault().trim())) {
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


    public  List<String> makeWebPage(Integer metadataId) throws IOException {

        return webPageUtil.makeWebPage(this,metadataId);
    }

}
