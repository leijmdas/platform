package com.kunlong.platform.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.platform.utils.JsonResult;

import java.util.Map;

public interface MetadataJoinService {
    JsonResult<Integer> makeDbTable(Integer metadataId);

    JsonResult<Integer> dropDbTable(Integer metadataId);

    PageResult<Map<String, Object>> checkDict(MetadataQueryDTO metadataQuery);

    PageResult<Map<String, Object>> selectTable(MetadataQueryDTO metadataQuery);


    Integer copyMaster(Integer metadataId);

    Boolean checkExistsFieldByMetadataId(Integer metadataId);

    JsonResult<Integer> deleteAllByMetadataId(Integer metadataId);
}