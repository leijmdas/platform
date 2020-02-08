package com.kunlong.dubbo.sys.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysUserQueryDTO;
import com.kunlong.dubbo.sys.model.AuthorizationDTO;
import com.kunlong.dubbo.sys.model.SysUserDTO;

import java.util.List;

public interface SysUserApiService {

	public PageResult<SysUserDTO> query(SysUserQueryDTO qp);
	
	public Integer save(SysUserDTO entity);
	
	public SysUserDTO findById(Integer userId);
	
	public void delete(Integer id);
	
	/**
	 * 分配角色
	 * @param id
	 * @param roleType
	 * @param roleIds
	 */
	public void assignRoles(Integer id, Integer roleType, List<Integer> roleIds);

	public List<SysUserDTO> findByIds(List<Integer> userIds);
	
	public SysUserDTO findByUsername(Integer corpId, String employNo);
	
	/**
	 * 校验密码
	 * @param username
	 * @param password
	 * @return
	 */
	public SysUserDTO checkPass(Integer corpId, String username, String password);

	/**
	 * 获取认证信息
	 * @param userId
	 * @return
	 */
	public AuthorizationDTO getAuthorization(Integer userId);

	/**
	 * 获取用户角色ID
	 * @param id
	 * @param type
	 * @return
	 */
	public List<Integer> findRoleIds(Integer id, Integer type);

	/**
	 * 修改密码
	 * @param userId
	 * @param oldPass
	 * @param newPass
	 * @return
	 */
	public SysUserDTO modifyPass(Integer userId, String oldPass, String newPass);

}
