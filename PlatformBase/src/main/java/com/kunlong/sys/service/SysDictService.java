package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysDict;
import com.kunlong.sys.domain.SysDictItem;
import com.kunlong.sys.queryParam.SysDictQueryParam;

import java.util.List;

/**
 * SysDictService
 * @author generator
 * @date 2015年12月15日
 */
public interface SysDictService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SysDict entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(SysDict entity);
	
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
	public SysDict findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param SysDict
	 * @return
	 */
	public List<SysDict> findByNotNullProps(SysDict entity);
	/**
	 * 通过主键更新非空属性
	 * @param SysDict
	 * @return
	 */
	public void updateNotNullPropsById(SysDict entity);
	
	
	public List<SysDict> findByQueryParam(SysDictQueryParam queryParam);

	public long countByQueryParam(SysDictQueryParam queryParam);
	
	public SysDict findByCode(Integer corpId, String code);

	public List<SysDictItem> findByDictDetails(Integer corpId, String code);
}
