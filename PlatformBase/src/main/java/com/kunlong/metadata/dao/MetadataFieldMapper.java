package com.kunlong.metadata.dao;


import com.kunlong.metadata.model.MetadataField;
import com.kunlong.metadata.model.MetadataFieldExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MetadataFieldMapper {
    long countByExample(MetadataFieldExample example);

    int deleteByExample(MetadataFieldExample example);

    int deleteByPrimaryKey(Integer fieldId);

    int insert(MetadataField record);

    int insertSelective(MetadataField record);

    List<MetadataField> selectByExample(MetadataFieldExample example);

    MetadataField selectByPrimaryKey(Integer fieldId);

    int updateByExampleSelective(@Param("record") MetadataField record, @Param("example") MetadataFieldExample example);

    int updateByExample(@Param("record") MetadataField record, @Param("example") MetadataFieldExample example);

    int updateByPrimaryKeySelective(MetadataField record);

    int updateByPrimaryKey(MetadataField record);
}