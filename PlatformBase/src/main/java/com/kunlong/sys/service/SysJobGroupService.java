package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysJobGroup;

import java.util.List;

/**
 * SysJobGroupService
 * @author generator
 * @date 2016年06月05日
 */
public interface SysJobGroupService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SysJobGroup entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(SysJobGroup entity);
	
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
	public SysJobGroup findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param SysJobGroup
	 * @return
	 */
	public List<SysJobGroup> findByNotNullProps(SysJobGroup entity);
	/**
	 * 通过主键更新非空属性
	 * @param SysJobGroup
	 * @return
	 */
	public void updateNotNullPropsById(SysJobGroup entity);
	
	public List<SysJobGroup> findByQueryParam(SysJobGroup.QueryParam queryParam);
	public long countByQueryParam(SysJobGroup.QueryParam queryParam);
	public int updateClause(SysJobGroup sysJob);
	public List<SysJobGroup> findByIds(List<Integer> ids);
	public List<SysJobGroup> findAll();
}
