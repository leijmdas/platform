package com.kunlong.system.service.impl;

import com.kunlong.system.dao.DictDatatypeMapper;
import com.kunlong.system.model.DictDatatype;
import com.kunlong.system.model.DictDatatypeExample;
import com.kunlong.system.service.DictDataTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Package: com.kunlong.sysmetadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
@Service
public class DictDataTypeServiceImpl implements DictDataTypeService {

    @Autowired
    DictDatatypeMapper dictDatatypeMapper;

    @Override
    public long countByExample(DictDatatypeExample example) {
        return 0;
    }

    @Override
    public int deleteByExample(DictDatatypeExample example) {
        return 0;
    }

    @Override
    public int deleteByPrimaryKey(Integer datainnerid) {
        return dictDatatypeMapper.deleteByPrimaryKey(datainnerid);


    }

    @Override
    public int insert(DictDatatype record) {
        return dictDatatypeMapper.insert(record);

    }

    @Override
    public int insertSelective(DictDatatype record) {
        return 0;
    }

    @Override
    public List<DictDatatype> selectByExample(DictDatatypeExample example) {
        return dictDatatypeMapper.selectByExample(example);

    }

    @Override
    public DictDatatype selectByPrimaryKey(Integer datainnerid) {
        return dictDatatypeMapper.selectByPrimaryKey(datainnerid);

    }

    @Override
    public int updateByExampleSelective(DictDatatype record, DictDatatypeExample example) {
        return 0;
    }

    @Override
    public int updateByExample(DictDatatype record, DictDatatypeExample example) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DictDatatype record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(DictDatatype record) {
        return dictDatatypeMapper.updateByPrimaryKey(record);

    }

    @Override
    public List<DictDatatype> selectDatatype() {
        return dictDatatypeMapper.selectDatatype( );

    }


}
