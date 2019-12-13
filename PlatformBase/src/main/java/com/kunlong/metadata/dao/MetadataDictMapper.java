package com.kunlong.metadata.dao;


import com.kunlong.metadata.model.MetadataDict;
import com.kunlong.metadata.model.MetadataDictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface MetadataDictMapper {
    long countByExample(MetadataDictExample example);

    int deleteByExample(MetadataDictExample example);

    int deleteByPrimaryKey(Integer metadataId);

    int insert(MetadataDict record);

    int insertSelective(MetadataDict record);

    List<MetadataDict> selectByExample(MetadataDictExample example);

    MetadataDict selectByPrimaryKey(Integer metadataId);

    int updateByExampleSelective(@Param("record") MetadataDict record, @Param("example") MetadataDictExample example);

    int updateByExample(@Param("record") MetadataDict record, @Param("example") MetadataDictExample example);

    int updateByPrimaryKeySelective(MetadataDict record);

    int updateByPrimaryKey(MetadataDict record);
}