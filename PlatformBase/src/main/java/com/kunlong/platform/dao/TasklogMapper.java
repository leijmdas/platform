package com.kunlong.platform.dao;

import com.kunlong.platform.domain.Tasklog;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import java.util.List;

public interface TasklogMapper extends HbatisMapper<Tasklog, Integer> {
    //-- 按实体参数查询[START]
    List<Tasklog> findByQueryParam(@Param("queryParam")Tasklog.QueryParam queryParam);

    long countByQueryParam(@Param("queryParam")Tasklog.QueryParam queryParam);

}
