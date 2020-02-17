//package com.kunlong.platform.service;
//
//import java.util.List;
///**
// * MetadataFieldService
// * @author generator
// * @date 2020年02月17日
// */
//public interface MetadataFieldService {
//
//	/**
//	 * 保存
//	 * @param entity
//	 */
//	public void save(MetadataField entity);
//
//	/**
//	 * 修改
//	 * @param entity
//	 */
//	public void update(MetadataField entity);
//
//	/**
//	 * 删除
//	 * @param id
//	 */
//	public void deleteById(Integer pk);
//
//	/**
//	 * 通过id获取
//	 * @param id
//	 * @return
//	 */
//	public MetadataField findById(Integer pk);
//	/**
//	 * 通过非空属性查询
//	 * @param MetadataField
//	 * @return
//	 */
//	public List<MetadataField> findByNotNullProps(MetadataField entity);
//	/**
//	 * 通过主键更新非空属性
//	 * @param MetadataField
//	 * @return
//	 */
//	public void updateNotNullPropsById(MetadataField entity);
//
//
//	/**
//	 * 通过实体参数分页查询
//	 * @param MetadataField.QueryParam
//	 * @return
//	 */
//	public List<MetadataField> findByQueryParam(MetadataField.QueryParam queryParam);
//	/**
//	 * 通过实体参数统计
//	 * @param MetadataField.QueryParam
//	 * @return
//	 */
//	public long countByQueryParam(MetadataField.QueryParam queryParam);
//	/**
//	 * 通过ID集合查询
//	 * @param List<Integer> pks
//	 * @return
//	 */
//	public List<MetadataField> findByIds(List<Integer> pks);
//	/**
//	 * 值填充
//	 * @param items
//	 * @param field
//	 */
//	public void fillValues(List<MetadataField> items,MetadataField.ValueField ...field);
//}
