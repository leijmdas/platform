package com.kunlong.sys.dao;

import com.kunlong.sys.domain.SysDictTreemodel;
import com.kunlong.sys.queryParam.SysDictTreemodelQueryParam;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;

import java.util.List;

/**
 * SysDictTreemodelMapper
 * @author generator
 * @date 2015年12月27日
 */
public interface SysDictTreemodelMapper extends HbatisMapper<SysDictTreemodel, Integer> {

	int replacePath(@Param("oldIdPath") String oldIdPath, @Param("newIdPath") String path);

	List<SysDictTreemodel> findByQueryParam(@Param("queryParam")
                                                    SysDictTreemodelQueryParam queryParam);

	long countByQueryParam(@Param("queryParam") SysDictTreemodelQueryParam queryParam);
	
}