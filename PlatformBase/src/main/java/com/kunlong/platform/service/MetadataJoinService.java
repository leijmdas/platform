package com.kunlong.platform.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.platform.domain.CheckDictResult;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.utils.JsonResult;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface MetadataJoinService {
    List<MetadataFieldModel> findAllByMetadataId(Integer metadataId, Integer limit);

    PageResult<Map<String, Object>> selectTable(MetadataQueryDTO q);

    PageResult<CheckDictResult> checkDict(Integer metadataId);

    JsonResult<Integer> makeDbTable(Integer metadataId);

    Boolean checkTableExists(Integer metadataId);

    JsonResult<Integer> dropDbTable(Integer metadataId);


    Integer copyMaster(Integer metadataId);

    Boolean checkExistsFieldByMetadataId(Integer metadataId);

    JsonResult<Integer> deleteAllByMetadataId(Integer metadataId);


    //WEB自动化工厂
    List<String> makeWebPage(Integer metadataId) throws IOException;

    void doSortMetadataDict(Integer subsysId, String ids);

    void doSortMetadataField(String ids);

    int dbImportTableFields(Integer metadataId);
}