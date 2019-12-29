package com.kunlong.metadata.service;

import com.kunlong.metadata.model.MetadataDict;
import com.kunlong.metadata.model.MetadataDictExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Package: com.kunlong.sysmetadata.service
 * Author: XZW
 * Date: Created in 2018/8/23 16:33
 */
public interface MetadataDictService {

    Integer copyMaster(Integer mid);
    Integer dpMaster(Integer mid);
    Boolean checkTableExists(String db,String tbl);

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
