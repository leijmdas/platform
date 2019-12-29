package com.kunlong.platform.service;

import com.kunlong.platform.domain.MetadataDictModel;
import java.util.List;
/**
 * MetadataDictModelService
 * @author generator
 * @date 2019年12月29日
 */
public interface MetadataDictModelService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MetadataDictModel entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(MetadataDictModel entity);
	
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
	public MetadataDictModel findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param MetadataDictModel
	 * @return
	 */
	public List<MetadataDictModel> findByNotNullProps(MetadataDictModel entity);
	/**
	 * 通过主键更新非空属性
	 * @param MetadataDictModel
	 * @return
	 */
	public void updateNotNullPropsById(MetadataDictModel entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param MetadataDictModel.QueryParam
	 * @return
	 */
	public List<MetadataDictModel> findByQueryParam(MetadataDictModel.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param MetadataDictModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(MetadataDictModel.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<MetadataDictModel> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<MetadataDictModel> items,MetadataDictModel.ValueField ...field);
}
