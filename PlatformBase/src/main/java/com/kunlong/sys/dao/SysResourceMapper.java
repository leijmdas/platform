package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysResource;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysResourceMapper
 * @author generator
 * @date 2015年12月05日
 */
public interface SysResourceMapper extends HbatisMapper<SysResource, Integer> {

	List<SysResource> findByQueryParam(@Param("queryParam") SysResource.QueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysResource.QueryParam queryParam);

	List<SysResource> findByIdsWithGroup(@Param("ids") List<Integer> ids);
}