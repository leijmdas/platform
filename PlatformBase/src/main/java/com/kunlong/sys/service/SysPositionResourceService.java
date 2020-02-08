package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysPositionResource;

import java.util.List;

/**
 * SysPositionResourceService
 * @author generator
 * @date 2015年12月05日
 */
public interface SysPositionResourceService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SysPositionResource entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(SysPositionResource entity);
	
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
	public SysPositionResource findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param SysPositionResource
	 * @return
	 */
	public List<SysPositionResource> findByNotNullProps(SysPositionResource entity);
	/**
	 * 通过主键更新非空属性
	 * @param SysPositionResource
	 * @return
	 */
	public void updateNotNullPropsById(SysPositionResource entity);
	
}
