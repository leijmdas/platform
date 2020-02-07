package com.kunlong.platform;

import com.kunlong.platform.context.BaseDao;

import java.util.List;

public abstract class BaseServiceImpl<T>    {

    protected abstract BaseDao<T> getMapper();

    public int insert(T record) {
        return getMapper().insert(record);
    }

    public List<T> select(T record) {
        return getMapper().select(record);
    }
}