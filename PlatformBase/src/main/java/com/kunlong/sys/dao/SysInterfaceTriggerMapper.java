package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysInterfaceTrigger;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

/**
 * SysInterfaceTriggerMapper
 * @author generator
 * @date 2016年06月06日
 */
public interface SysInterfaceTriggerMapper extends HbatisMapper<SysInterfaceTrigger, Integer> {
	long deleteTriggerIdInterfaceId(@Param("sysInterfaceTrigger") SysInterfaceTrigger sysInterfaceTrigger);
}