package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysUserRole;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysUserRoleMapper
 * @author generator
 * @date 2015年12月05日
 */
public interface SysUserRoleMapper extends HbatisMapper<SysUserRole, Integer> {

	List<SysUserRole> findByQueryParam(@Param("queryParam") SysUserRole.QueryParam queryParam);
}