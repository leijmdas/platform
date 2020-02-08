package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysPosition;
import com.kunlong.sys.domain.SysResource;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.sys.queryParam.SysPositionQueryParam;

import java.util.List;

/**
 * SysPositionService
 * @author generator
 * @date 2015年12月05日
 */
public interface SysPositionService {
	
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SysPosition entity);

	/**
	 * 修改
	 * @param entity
	 */
	public void update(SysPosition entity);
	
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
	public SysPosition findById(Integer pk);
	/**
	 * 通过非空属性查询
	 * @param SysPosition
	 * @return
	 */
	public List<SysPosition> findByNotNullProps(SysPosition entity);
	/**
	 * 通过主键更新非空属性
	 * @param SysPosition
	 * @return
	 */
	public void updateNotNullPropsById(SysPosition entity);
	
	public List<SysPosition> findAllAvaliable();
	/**
	 * 通过实体参数查询
	 * @param queryParam
	 * @return
	 */
	public List<SysPosition> findByQueryParam(SysPositionQueryParam queryParam);

	public long countByQueryParam(SysPositionQueryParam queryParam);
	
	/**
	 * 查询级联
	 * @param orgId
	 * @return
	 */
	public List<SysPosition> findCascadePositionsByOrgId(Integer orgId);

	public List<SysPosition> findUserPositions(Integer userId);
	
	/**
	 * 分配岗位资源
	 * @param id
	 * @param resIdList
	 */
	public void assignPositionResources(Integer id, List<Integer> resIdList);
	
	/**
	 * 获取岗位资源
	 * @param asList
	 * @return
	 */
	public List<SysResourceGroup> findPositionResources(List<Integer> positionIds);
	
	List<SysResource> findPositionResources(Integer positionId);
	
}
