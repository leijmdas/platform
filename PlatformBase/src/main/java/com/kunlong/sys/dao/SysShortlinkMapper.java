package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysShortlink;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysShortlinkMapper
 * @author generator
 * @date 2018年10月11日
 */
public interface SysShortlinkMapper extends HbatisMapper<SysShortlink, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<SysShortlink> findByQueryParam(@Param("queryParam") SysShortlink.QueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysShortlink.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}