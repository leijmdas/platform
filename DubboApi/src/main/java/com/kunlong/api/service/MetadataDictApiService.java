package com.kunlong.api.service;

import com.kunlong.api.dto.queryParam.MetadataDictModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;

import java.util.List;

public interface MetadataDictApiService {
    /**
     * 通过实体参数分页查询
     * @param
     * @return
     */
    public List<MetadataDictModelDTO> query(MetadataDictModelQueryDTO queryParam);

    public MetadataDictModelDTO findById(Integer pk);
    /**
     * 通过非空属性查询
     * @param
     * @return
     */
    public List<MetadataDictModelDTO> findByNotNullProps(MetadataDictModelDTO entity);
    /**
     * 通过主键更新非空属性
     * @param entity
     * @return
     */
    public void updateNotNullPropsById(MetadataDictModelDTO entity);



}
