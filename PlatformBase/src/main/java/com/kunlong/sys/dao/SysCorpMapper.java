package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysCorp;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysCorpMapper
 * @author generator
 * @date 2018年06月07日
 */
public interface SysCorpMapper extends HbatisMapper<SysCorp, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<SysCorp> findByQueryParam(@Param("queryParam") SysCorp.QueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysCorp.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}