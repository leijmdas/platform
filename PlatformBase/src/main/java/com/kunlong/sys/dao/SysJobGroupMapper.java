package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysJobGroup;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysJobGroupMapper
 * @author generator
 * @date 2016年06月05日
 */
public interface SysJobGroupMapper extends HbatisMapper<SysJobGroup, Integer> {
	List<SysJobGroup> findByQueryParam(@Param("queryParam") SysJobGroup.QueryParam queryParam);
	long countByQueryParam(@Param("queryParam") SysJobGroup.QueryParam queryParam);
	int updateClause(@Param("sysJobGroup") SysJobGroup sysJobGroup);
}