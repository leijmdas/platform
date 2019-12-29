package com.kunlong.api.service;

import com.kunlong.api.dto.queryParam.MetadataDictModelQueryDTO;
import com.kunlong.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;

import java.util.List;

public interface MetadataFieldApiService {
    /**
     * 通过实体参数分页查询
     * @param
     * @return
     */
    public List<MetadataFieldModelDTO> query(MetadataFieldModelQueryDTO queryParam);
    public long countByQueryParam(MetadataFieldModelQueryDTO queryParam);

    public List<MetadataFieldModelDTO> query(String tableName);

    public MetadataFieldModelDTO findById(Integer pk);
    /**
     * 通过非空属性查询
     * @param
     * @return
     */
    public List<MetadataFieldModelDTO> findByNotNullProps(MetadataFieldModelDTO entity);
    /**
     * 通过主键更新非空属性
     * @param entity
     * @return
     */
    public void updateNotNullPropsById(MetadataFieldModelDTO entity);



}
