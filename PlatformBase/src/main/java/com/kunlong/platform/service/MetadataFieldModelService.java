package com.kunlong.platform.service;

import com.kunlong.platform.domain.MetadataFieldModel;
import java.util.List;
/**
 * MetadataFieldModelService
 * @author generator
 * @date 2019年12月29日
 */
public interface MetadataFieldModelService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MetadataFieldModel entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(MetadataFieldModel entity);
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer pk);
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public MetadataFieldModel findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param MetadataFieldModel
	 * @return
	 */
	public List<MetadataFieldModel> findByNotNullProps(MetadataFieldModel entity);
	/**
	 * 通过主键更新非空属性
	 * @param MetadataFieldModel
	 * @return
	 */
	public void updateNotNullPropsById(MetadataFieldModel entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param MetadataFieldModel.QueryParam
	 * @return
	 */
	public List<MetadataFieldModel> findByQueryParam(MetadataFieldModel.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param MetadataFieldModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(MetadataFieldModel.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<MetadataFieldModel> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<MetadataFieldModel> items,MetadataFieldModel.ValueField ...field);
}
