package com.kunlong.platform.service;

import com.kunlong.platform.domain.DictDataModel;
import java.util.List;
/**
 * DictDataModelService
 * @author generator
 * @date 2020年02月20日
 */
public interface DictDataModelService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(DictDataModel entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(DictDataModel entity);
	
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
	public DictDataModel findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param DictDataModel
	 * @return
	 */
	public List<DictDataModel> findByNotNullProps(DictDataModel entity);
	/**
	 * 通过主键更新非空属性
	 * @param DictDataModel
	 * @return
	 */
	public void updateNotNullPropsById(DictDataModel entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param DictDataModel.QueryParam
	 * @return
	 */
	public List<DictDataModel> findByQueryParam(DictDataModel.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param DictDataModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(DictDataModel.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<DictDataModel> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<DictDataModel> items,DictDataModel.ValueField ...field);
}
