package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysJobTrigger;
import com.kunlong.sys.queryParam.SysJobTriggerParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * SysJobTriggerMapper
 * @author generator
 * @date 2016年06月01日
 */
public interface SysJobTriggerMapper extends HbatisMapper<SysJobTrigger, Integer> {
	List<SysJobTrigger> findByQueryParam(@Param("queryParam") SysJobTriggerParam queryParam);
	long countByQueryParam(@Param("queryParam") SysJobTriggerParam queryParam);
	SysJobTrigger findJobTriggerById(@PathVariable("id") Integer id);
	int updateClause(@Param("sysJobTrigger") SysJobTrigger sysJobTrigger);

	List<SysJobTrigger> findAllTriggers(@Param("jobType") Integer jobType);
}