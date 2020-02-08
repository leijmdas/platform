package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysResourceGroup;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysResourceGroupMapper
 * @author generator
 * @date 2015年12月05日
 */
public interface SysResourceGroupMapper extends HbatisMapper<SysResourceGroup, Integer> {

	List<SysResourceGroup> findRoleResources(@Param("roleIds") List<Integer> roleIds);

	/**
	 * 按分类查询全部
	 * @param resType
	 * @return
	 */
	List<SysResourceGroup> findAllByType(@Param("domainId") Integer domainId, @Param("groupType") Byte resType);


	List<SysResourceGroup> findPositionResources(@Param("positionIds") List<Integer> positionIds);
	
	
}