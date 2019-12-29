package com.kunlong.metadata.service.impl;

import com.kunlong.metadata.dao.MetadataFieldMapper;
import com.kunlong.metadata.model.MetadataField;
import com.kunlong.metadata.model.MetadataFieldExample;
import com.kunlong.metadata.service.MetadataFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Package: com.kunlong.sysmetadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@Component
public class MetadataFieldServiceImpl implements MetadataFieldService {
    @Autowired
    MetadataFieldMapper metadataFieldMapper;

    //@Autowired
    //MetadataFieldMapper sysMetaDataFieldMapper;

    @Override
    public long countByExample(MetadataFieldExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(MetadataFieldExample example) {
       return metadataFieldMapper.deleteByExample(example);

    }

    @Override
    public int deleteByPrimaryKey(Integer fieldId) {
        return  metadataFieldMapper.deleteByPrimaryKey(fieldId);


    }

    public int insert(MetadataField record) {
        return 0;
    }

    @Override
    public int insertSelective(MetadataField record) {
        return  metadataFieldMapper.insertSelective(record);


    }

    @Override
    public List<MetadataField> selectByExample(MetadataFieldExample example) {
        return  metadataFieldMapper.selectByExample(example);


    }



    @Override
    public MetadataField selectByPrimaryKey(Integer fieldId) {
        return metadataFieldMapper.selectByPrimaryKey(fieldId);


    }

    @Override
    public int updateByExampleSelective(MetadataField record, MetadataFieldExample example) {
        return 0;
    }

    @Override
    public int updateByExample(MetadataField record, MetadataFieldExample example) {
        return  metadataFieldMapper.updateByExample(record, example);

    }

    @Override
    public int updateByPrimaryKeySelective(MetadataField record) {
        return metadataFieldMapper.updateByPrimaryKeySelective(record);

    }

    @Override
    public int updateByPrimaryKey(MetadataField record) {
        return  metadataFieldMapper.updateByPrimaryKeySelective(record);


    }
}
