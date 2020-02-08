package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysPosition;
import com.kunlong.sys.queryParam.SysPositionQueryParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysOrgMapper
 * @author generator
 * @date 2015年12月05日
 */
public interface SysPositionMapper extends HbatisMapper<SysPosition, Integer> {
	
	/**
	 * 通过实体参数查询
	 * @param queryParam
	 * @return
	 */
	List<SysPosition> findByQueryParam(@Param("queryParam") SysPositionQueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysPositionQueryParam queryParam);

	/**
	 * 通过部门查询级联岗位
	 * @param orgId
	 * @return
	 */
	List<SysPosition> findCascadePositionsByOrgId(@Param("orgId") Integer orgId);

	List<SysPosition> findUserPositions(@Param("userId") Integer userId);
	
}