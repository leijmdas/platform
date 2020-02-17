//package com.kunlong.platform.dao;
//
//import java.util.List;
//
//import org.apache.ibatis.annotations.Param;
//import org.mybatis.hbatis.orm.mapper.HbatisMapper;
//
///**
// * MetadataFieldMapper
// *
// * @author generator
// * @date 2020年02月17日
// */
//public interface MetadataFieldMapper extends HbatisMapper<MetadataField, Integer> {
//
//
//    //-- 按实体参数查询[START]
//    List<MetadataField> findByQueryParam(@Param("queryParam") MetadataField.QueryParam queryParam);
//
//    long countByQueryParam(@Param("queryParam") MetadataField.QueryParam queryParam);
//    //-- 按实体参数查询[END]
//
//    //-- 自定义业务方法，请写在下方 -->
//}