package com.kunlong.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import com.kunlong.platform.domain.SubsysDict;
/**
 * SubsysDictMapper
 * @author generator
 * @date 2019年12月29日
 */
public interface SubsysDictMapper extends HbatisMapper<SubsysDict, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<SubsysDict> findByQueryParam(@Param("queryParam")SubsysDict.QueryParam queryParam);
	
	long countByQueryParam(@Param("queryParam")SubsysDict.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}