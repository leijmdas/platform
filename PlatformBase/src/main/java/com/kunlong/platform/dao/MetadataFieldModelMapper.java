package com.kunlong.platform.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.mybatis.hbatis.orm.mapper.HbatisMapper;
import com.kunlong.platform.domain.MetadataFieldModel;
/**
 * MetadataFieldModelMapper
 * @author generator
 * @date 2019年12月29日
 */
public interface MetadataFieldModelMapper extends HbatisMapper<MetadataFieldModel, Integer> {
	
	
	//-- 按实体参数查询[START] 
	List<MetadataFieldModel> findByQueryParam(@Param("queryParam")MetadataFieldModel.QueryParam queryParam);
	
	long countByQueryParam(@Param("queryParam")MetadataFieldModel.QueryParam queryParam);
	//-- 按实体参数查询[END] 
	
	//-- 自定义业务方法，请写在下方 -->
}