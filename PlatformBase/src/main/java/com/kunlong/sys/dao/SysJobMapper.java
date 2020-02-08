package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysJob;
import com.kunlong.sys.queryParam.SysJobParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysJobMapper
 * @author generator
 * @date 2016年06月01日
 */
public interface SysJobMapper extends HbatisMapper<SysJob, Integer> {
	
	List<SysJob> findByQueryParam(@Param("queryParam") SysJobParam queryParam);
	long countByQueryParam(@Param("queryParam") SysJobParam queryParam);
	int updateClause(@Param("sysJob") SysJob sysJob);
}