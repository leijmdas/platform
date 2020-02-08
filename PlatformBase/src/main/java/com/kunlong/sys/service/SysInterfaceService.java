package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysInterface;
import com.kunlong.sys.domain.SysJobTrigger;
import com.kunlong.sys.queryParam.SysInterfaceParam;

import java.util.List;

/**
 * SysInterfaceService
 * 
 * @author generator
 * @date 2016年06月06日
 */
public interface SysInterfaceService {

	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public void save(SysInterface entity);

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	public void update(SysInterface entity);

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteById(Integer pk);

	/**
	 * 通过id获取
	 * 
	 * @param id
	 * @return
	 */
	public SysInterface findById(Integer pk);

	/**
	 * 通过非空属性查询
	 * 
	 * @param SysInterface
	 * @return
	 */
	public List<SysInterface> findByNotNullProps(SysInterface entity);

	/**
	 * 通过主键更新非空属性
	 * 
	 * @param SysInterface
	 * @return
	 */
	public void updateNotNullPropsById(SysInterface entity);

	public List<SysInterface> findByQueryParam(SysInterfaceParam queryParam);

	public long countByQueryParam(SysInterfaceParam queryParam);

	public List<SysJobTrigger> findTriggerByInterface(SysInterfaceParam queryParam);

	/**
	 * 分配触发器
	 * 
	 * @param id
	 * @param idList
	 */
	public void assignTriggers(Integer id, List<Integer> idList);

	public List<SysInterface> findByTriggerId(Integer triggerId, Byte... status);

	/**
	 * 调用接口
	 * 
	 * @param intf
	 */
	public void invokeService(SysInterface intf);
}
