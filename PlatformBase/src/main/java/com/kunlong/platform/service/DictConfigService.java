package com.kunlong.platform.service;

import com.kunlong.platform.domain.DictConfig;
import java.util.List;
/**
 * DictConfigService
 * @author generator
 * @date 2019年12月29日
 */
public interface DictConfigService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(DictConfig entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(DictConfig entity);
	
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
	public DictConfig findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param DictConfig
	 * @return
	 */
	public List<DictConfig> findByNotNullProps(DictConfig entity);
	/**
	 * 通过主键更新非空属性
	 * @param DictConfig
	 * @return
	 */
	public void updateNotNullPropsById(DictConfig entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param DictConfig.QueryParam
	 * @return
	 */
	public List<DictConfig> findByQueryParam(DictConfig.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param DictConfig.QueryParam
	 * @return
	 */
	public long countByQueryParam(DictConfig.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<DictConfig> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<DictConfig> items,DictConfig.ValueField ...field);
}
