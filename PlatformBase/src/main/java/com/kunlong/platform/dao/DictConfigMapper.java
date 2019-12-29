package com.kunlong.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import com.kunlong.platform.domain.DictConfig;
/**
 * DictConfigMapper
 * @author generator
 * @date 2019年12月29日
 */
public interface DictConfigMapper extends HbatisMapper<DictConfig, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<DictConfig> findByQueryParam(@Param("queryParam")DictConfig.QueryParam queryParam);
	
	long countByQueryParam(@Param("queryParam")DictConfig.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}