package com.kunlong.dubbo.sys.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysDictQueryDTO;
import com.kunlong.dubbo.sys.model.SysDictDTO;
import com.kunlong.dubbo.sys.model.SysDictItemDTO;

import java.util.List;

/**
 * 字典服务
 * @name SysDictApiService
 * @author zz  | www.xwparking.com
 * @date 2018年12月18日  
 * @description:
 */
public interface SysDictApiService {

	/**
	 * 查询
	 * @param qp
	 * @return
	 */
	public PageResult<SysDictDTO> query(SysDictQueryDTO qp);
	
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 */
	public SysDictDTO findWithItemsById(Integer id);
	
	/**
	 * 保存字典项
	 * @param id
	 * @return
	 */
	public Integer saveDictItem(SysDictItemDTO item);
	
	
	/**
	 * 删除字典项
	 * @param itemId
	 */
	public void deleteItem(Integer itemId);
	
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 */
	public SysDictDTO findWithItemsByCode(Integer corpId, String code);
	
	SysDictItemDTO findItemById(Integer itemId);
	
	/**
	 * 按ID查询
	 * @param id
	 * @return
	 */
	List<SysDictItemDTO> findItemsByIds(List<Integer> itemIds);
	
}
