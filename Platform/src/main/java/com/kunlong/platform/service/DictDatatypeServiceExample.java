package com.kunlong.platform.service;

import com.kunlong.platform.dao.DictDatatypeDAO;
import com.kunlong.platform.model.DictDatatypeDemo;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictDatatypeServiceExample implements DictDatatypeDAO {
    @Autowired
    DictDatatypeDAO dictDatatypeDAO;


    public List<DictDatatypeDemo> selectAll() {
        return dictDatatypeDAO.selectAll();
    }

    public DictDatatypeDemo selectOne(DictDatatypeDemo d) {
        return dictDatatypeDAO.selectOne(d);
    }

    @Override
    public int deleteByPrimaryKey(Object o) {
        return 0;
    }

    @Override
    public int delete(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public int insert(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public int insertSelective(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public boolean existsWithPrimaryKey(Object o) {
        return false;
    }

    @Override
    public DictDatatypeDemo selectByPrimaryKey(Object o) {
        return null;
    }

    @Override
    public int selectCount(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public List<DictDatatypeDemo> select(DictDatatypeDemo dictDatatypeDemo) {
        return null;
    }

    @Override
    public int updateByPrimaryKey(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }

    @Override
    public int deleteByExample(Object o) {
        return 0;
    }

    @Override
    public List<DictDatatypeDemo> selectByExample(Object o) {
        return null;
    }

    @Override
    public int selectCountByExample(Object o) {
        return 0;
    }

    @Override
    public DictDatatypeDemo selectOneByExample(Object o) {
        return null;
    }

    @Override
    public int updateByExample(DictDatatypeDemo dictDatatypeDemo, Object o) {
        return 0;
    }

    @Override
    public int updateByExampleSelective(DictDatatypeDemo dictDatatypeDemo, Object o) {
        return 0;
    }

    @Override
    public List<DictDatatypeDemo> selectByExampleAndRowBounds(Object o, RowBounds rowBounds) {
        return null;
    }

    @Override
    public List<DictDatatypeDemo> selectByRowBounds(DictDatatypeDemo dictDatatypeDemo, RowBounds rowBounds) {
        return null;
    }

    @Override
    public int insertList(List<? extends DictDatatypeDemo> list) {
        return 0;
    }

    @Override
    public int insertUseGeneratedKeys(DictDatatypeDemo dictDatatypeDemo) {
        return 0;
    }
}
