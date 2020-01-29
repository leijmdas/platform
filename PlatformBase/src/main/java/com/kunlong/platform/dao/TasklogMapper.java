package com.kunlong.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import com.kunlong.platform.domain.Tasklog;
/**
 * TasklogMapper
 * @author generator
 * @date 2020年01月30日
 */
public interface TasklogMapper extends HbatisMapper<Tasklog, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<Tasklog> findByQueryParam(@Param("queryParam")Tasklog.QueryParam queryParam);
	
	long countByQueryParam(@Param("queryParam")Tasklog.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}