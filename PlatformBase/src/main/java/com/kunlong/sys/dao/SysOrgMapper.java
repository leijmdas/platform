package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysOrg;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysOrgMapper
 * @author generator
 * @date 2015年12月05日
 */
public interface SysOrgMapper extends HbatisMapper<SysOrg, Integer> {
	
	/**
	 * 通过实体参数查询
	 * @param queryParam
	 * @return
	 */
	List<SysOrg> findByQueryParam(@Param("queryParam") SysOrg.QueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysOrg.QueryParam queryParam);

	int replacePath(@Param("oldIdPath") String oldIdPath, @Param("newIdPath") String path);
	
}