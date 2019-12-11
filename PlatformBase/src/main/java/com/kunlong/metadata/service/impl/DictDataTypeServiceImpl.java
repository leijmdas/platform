package com.kunlong.metadata.service.impl;

import com.kunlong.metadata.dao.DictDatatypeMapper;
import com.kunlong.metadata.model.DictDatatype;
import com.kunlong.metadata.model.DictDatatypeExample;
import com.kunlong.metadata.service.DictDataTypeService;
import org.apache.ibatis.session.SqlSession;


import java.util.List;

/**
 * Package: ytb.manager.metadata.service.impl
 * Author: XZW
 * Date: Created in 2018/8/23 16:50
 */
public class DictDataTypeServiceImpl implements DictDataTypeService {


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

        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.deleteByPrimaryKey(datainnerid);
        } catch(Exception e){
            e.printStackTrace();
        }
        finally {
            sq.close();
        }
        return 0;
    }

    @Override
    public int insert(DictDatatype record) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.insert(record );
        } finally {
            sq.close();
        }
    }

    @Override
    public int insertSelective(DictDatatype record) {
        return 0;
    }

    @Override
    public List<DictDatatype> selectByExample(DictDatatypeExample example) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.selectByExample(example);
        } finally {
            sq.close();
        }
    }

    @Override
    public DictDatatype selectByPrimaryKey(Integer datainnerid) {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.selectByPrimaryKey(datainnerid);
        } finally {
            sq.close();
        }
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
        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.updateByPrimaryKey(record );
        } finally {
            sq.close();
        }
    }

    @Override
    public List<DictDatatype> selectDatatype() {
        SqlSession sq = MyBatisUtil.getSession();
        try {
            DictDatatypeMapper dictDatatypeMapper = sq.getMapper(DictDatatypeMapper.class);
            return dictDatatypeMapper.selectDatatype();
        } finally {
            sq.close();
        }
    }


}
