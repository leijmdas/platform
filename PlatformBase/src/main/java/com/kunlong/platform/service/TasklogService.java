package com.kunlong.platform.service;

import com.kunlong.platform.domain.Tasklog;
import java.util.List;
/**
 * TasklogService
 * @author generator
 * @date 2020年01月30日
 */
public interface TasklogService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(Tasklog entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(Tasklog entity);
	
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
	public Tasklog findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param Tasklog
	 * @return
	 */
	public List<Tasklog> findByNotNullProps(Tasklog entity);
	/**
	 * 通过主键更新非空属性
	 * @param Tasklog
	 * @return
	 */
	public void updateNotNullPropsById(Tasklog entity);
	
	
	/**
	 * 通过实体参数分页查询
	 * @param Tasklog.QueryParam
	 * @return
	 */
	public List<Tasklog> findByQueryParam(Tasklog.QueryParam queryParam);
	/**
	 * 通过实体参数统计
	 * @param Tasklog.QueryParam
	 * @return
	 */
	public long countByQueryParam(Tasklog.QueryParam queryParam);
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<Tasklog> findByIds(List<Integer> pks);
	/**
	 * 值填充
	 * @param items
	 * @param field
	 */
	public void fillValues(List<Tasklog> items,Tasklog.ValueField ...field);
}
