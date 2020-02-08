package com.kunlong.platform.dubbo.api.provider;

import app.support.query.PageResult;
import com.kunlong.platform.dubbo.api.DTFactory;
import com.kunlong.dubbo.sys.dto.queryParam.SysUserQueryDTO;
import com.kunlong.dubbo.sys.model.*;
import com.kunlong.dubbo.sys.service.SysUserApiService;
import com.kunlong.platform.dubbo.api.transformer.SysUserTransformer;
import com.kunlong.sys.domain.SysCorp;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.sys.domain.SysRole;
import com.kunlong.sys.domain.SysUser;
import com.kunlong.sys.service.SysCorpService;
import com.kunlong.sys.service.SysRoleService;
import com.kunlong.sys.service.SysUserService;
import com.kunlong.core.util.BeanMapper;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service(version = "${dubbo.service.version}")
public class SysUserApiProvider implements SysUserApiService {

	@Autowired
	private SysUserService service;
	
	@Autowired
	private SysRoleService sysRoleService;
	
	@Autowired
	private SysCorpService sysCorpService;
	
	private static final SysUserTransformer T = DTFactory.getInstance(SysUserTransformer.class);
	
	@Override
	public PageResult<SysUserDTO> query(SysUserQueryDTO qpDTO) {
		com.kunlong.sys.domain.SysUser.QueryParam qp = BeanMapper.getInstance().map(qpDTO, com.kunlong.sys.domain.SysUser.QueryParam.class);
		//System.out.println(qpDTO);
		//qp.setSortBys(qpDTO.getOrderBys());
		List<SysUser> list = this.service.findByQueryParam(qp);

		long total = this.service.countByQueryParam(qp);
		
		PageResult<SysUserDTO> page = new PageResult<SysUserDTO>();
		page.setData(T.produces(list));
		page.setTotal(total);
		return page;
	}

	@Override
	public Integer save(SysUserDTO entityDTO) {
		SysUser entity = T.consume(entityDTO);
		if(entity.getId() == null) {
			this.service.save(entity);
		} else {
			SysUser tmp = this.service.findById(entity.getId());
			BeanMapper.getInstance().map(entity, tmp);
			this.service.update(tmp);
		}
		return entity.getId();
	}

	@Override
	public SysUserDTO findById(Integer id) {
		SysUser tmp = this.service.findById(id);
		return T.produce(tmp);
	}

	@Override
	public void delete(Integer id) {
		this.service.deleteById(id);
	}

	@Override
	public void assignRoles(Integer id, Integer roleType, List<Integer> roleIds) {
		this.service.assignRoles(id, roleType, roleIds);
	}

	@Override
	public List<SysUserDTO> findByIds(List<Integer> userIds) {
		List<SysUser> suList = this.service.findByIds(userIds);
		return T.produces(suList);
	}

	@Override
	public SysUserDTO findByUsername(Integer corpId, String employeeNo) {
		SysUser su = this.service.findByUsername(corpId, employeeNo);
		
		return T.produce(su);
	}

	@Override
	public SysUserDTO checkPass(Integer corpId, String username, String password) {
		SysUser su = this.service.checkPass(corpId,username,password);
		
		return T.produce(su);
	}

	@Override
	public AuthorizationDTO getAuthorization(Integer userId) {
		SysUser su = this.service.findById(userId);
		
		Assert.notNull(su,"数据错误,用户不存在");
		
		List<SysRole> sysRoles = this.sysRoleService.findUserRoles(su.getId(), SysRole.TYPE_FUNCTION);

		SysUserDTO miniSU = new SysUserDTO();
		List<SysRoleDTO> roleDTOs = new ArrayList<SysRoleDTO>();
		List<SysResourceGroupDTO> resGroupDTOs = new ArrayList<SysResourceGroupDTO>();

		BeanMapper.getInstance().map(su, miniSU);

		if (sysRoles != null && sysRoles.size() > 0) {
			List<Integer> roleIds = new ArrayList<Integer>(sysRoles.size());
			for (SysRole sr : sysRoles) {
				SysRoleDTO srDTO = new SysRoleDTO();
				BeanMapper.getInstance().map(sr, srDTO);
				roleDTOs.add(srDTO);

				roleIds.add(sr.getId());
			}

			if (roleIds != null && roleIds.size() > 0) {
				List<SysResourceGroup> resGroups = this.sysRoleService.findRoleResources(roleIds);

				if (resGroups != null && resGroups.size() > 0) {
					for (SysResourceGroup resGroup : resGroups) {
						SysResourceGroupDTO tmp = new SysResourceGroupDTO();
						BeanMapper.getInstance().map(resGroup, tmp);
						resGroupDTOs.add(tmp);

					}
				}
			}
		}
		SysCorp corp = this.sysCorpService.findById(su.getCorpId());
		AuthorizationDTO az = new AuthorizationDTO();
		
		SysCorpDTO scd = new SysCorpDTO();
		scd.setId(corp.getId());
		scd.setCorpCode(corp.getCorpCode());
		scd.setCorpName(corp.getCorpName());
		az.setSysCorp(scd);
		
		
		az.setSysUser(miniSU);
		az.setSysRoles(roleDTOs);
		az.setSysResourceGroups(resGroupDTOs);
		
		return az;
	}

	@Override
	public List<Integer> findRoleIds(Integer userId, Integer type) {
		return this.service.findRoleIds(userId, type);
	}

	@Override
	public SysUserDTO modifyPass(Integer userId, String oldPass, String newPass) {
		SysUser su = this.service.modifyPass(userId,oldPass,newPass);
		return T.produce(su);
	}

}
