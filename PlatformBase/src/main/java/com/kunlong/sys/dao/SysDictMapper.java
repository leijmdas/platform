package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysDict;
import com.kunlong.sys.queryParam.SysDictQueryParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysDictMapper
 * @author generator
 * @date 2015年12月15日
 */
public interface SysDictMapper extends HbatisMapper<SysDict, Integer> {

	List<SysDict> findByQueryParam(@Param("queryParam") SysDictQueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysDictQueryParam queryParam);


	SysDict findByCode(@Param("corpId") Integer corpId, @Param("code") String code);
}