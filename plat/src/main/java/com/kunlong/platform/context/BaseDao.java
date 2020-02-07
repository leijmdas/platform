package com.kunlong.platform.context;

import tk.mybatis.mapper.common.*;
import tk.mybatis.mapper.common.condition.SelectCountByConditionMapper;
import tk.mybatis.mapper.common.example.SelectCountByExampleMapper;
import tk.mybatis.mapper.common.rowbounds.SelectByExampleRowBoundsMapper;
import tk.mybatis.mapper.common.rowbounds.SelectRowBoundsMapper;


public interface BaseDao<T> extends BaseMapper<T>,
        SelectCountByExampleMapper<T>,
        SelectByExampleRowBoundsMapper<T>,
        SelectRowBoundsMapper<T>,
        SelectCountByConditionMapper<T>,
        MySqlMapper<T>, IdsMapper<T>, ConditionMapper<T>,
        ExampleMapper<T> {


}