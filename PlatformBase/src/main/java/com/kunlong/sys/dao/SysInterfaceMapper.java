package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysInterface;
import com.kunlong.sys.domain.SysJobTrigger;
import com.kunlong.sys.queryParam.SysInterfaceParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysInterfaceMapper
 * @author generator
 * @date 2016年06月06日
 */
public interface SysInterfaceMapper extends HbatisMapper<SysInterface, Integer> {
	
	List<SysInterface> findByQueryParam(@Param("queryParam") SysInterfaceParam queryParam);
	long countByQueryParam(@Param("queryParam") SysInterfaceParam queryParam);
	List<SysJobTrigger> findTriggerByInterface(@Param("queryParam") SysInterfaceParam queryParam);
	List<SysInterface> findByTriggerId(@Param("triggerId") Integer triggerId, @Param("statusList") List<Byte> statusList);

}