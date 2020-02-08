package com.kunlong.dubbo.sys.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysHttpJobQueryDTO;
import com.kunlong.dubbo.sys.model.SysHttpJobDTO;

import java.util.List;

/**
 * 
 * @name SysHttpJobApiService
 * 
 * @date 2019年03月23日  
 * @description:
 */
public interface SysHttpJobApiService {

	/**
	 * 查询
	 * @param qp
	 * @return
	 */
	public PageResult<SysHttpJobDTO> query(SysHttpJobQueryDTO qp);
	
	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public Integer save(SysHttpJobDTO entity);
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 */
	public SysHttpJobDTO findById(Integer id);
	
	/**
	 * 删除
	 * @param id
	 */
	boolean delete(Integer id);
	/**
	 * 修改
	 * @param entity
	 * @return 
	 */
     Integer update(SysHttpJobDTO entity);
	
	/**
	 * 通过ID查询
	 * @param ids
	 * @return
	 */
	public List<SysHttpJobDTO> findByIds(List<Integer> ids);

	//public List<Integer> findResourceIds(Integer id);

	//public void assignRoleResources(Integer id, List<Integer> resIds);
	Integer pause(Integer id);
	
	Integer resume(Integer id);
	
}
