package com.kunlong.platform.service;

import com.kunlong.platform.domain.SubsysDict;
import java.util.List;
/**
 * SubsysDictService
 * @author generator
 * @date 2019年12月29日
 */
public interface SubsysDictService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SubsysDict entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(SubsysDict entity);
	
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
	public SubsysDict findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param SubsysDict
	 * @return
	 */
	public List<SubsysDict> findByNotNullProps(SubsysDict entity);
	/**
	 * 通过主键更新非空属性
	 * @param SubsysDict
	 * @return
	 */
	public void updateNotNullPropsById(SubsysDict entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param SubsysDict.QueryParam
	 * @return
	 */
	public List<SubsysDict> findByQueryParam(SubsysDict.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param SubsysDict.QueryParam
	 * @return
	 */
	public long countByQueryParam(SubsysDict.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<SubsysDict> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<SubsysDict> items,SubsysDict.ValueField ...field);
}
