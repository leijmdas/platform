package com.kunlong.sys.service.impl;

import com.kunlong.sys.support.CurrentRequestContext;
import com.kunlong.sys.dao.SysUserMapper;
import com.kunlong.sys.domain.*;
import com.kunlong.sys.service.*;
import com.kunlong.core.exception.BusinessException;
import com.kunlong.core.util.CollectionUtil;
import com.kunlong.core.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * SysUserServiceImpl
 * @author generator
 * @date 2015年12月05日
 */
@Service("asysUserServiceImpl")
public class SysUserServiceImpl implements SysUserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(SysUserServiceImpl.class);

	@Autowired
	private SysUserMapper repo;

	@Autowired
	private SysCorpService sysCorpService;

	@Autowired
	private SysOrgService sysOrgService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private SysUserRoleService sysUserRoleService;

	@Autowired
	private SysUserPositionService sysUserPositionService;

	@Autowired
	private SysPositionService sysPositionService;

	@Autowired
	private SysAccountService sysAccountService;
	
	private static final String DEFAULT_PWD = "111111";
	

	private SysUser checkEntity(SysUser entity) {
		
		SysUser tmp = this.findByUsername(entity.getCorpId(), entity.getUsername());
		if (tmp != null && !tmp.getId().equals(entity.getId())) {
			throw new RuntimeException("用户已存在");
		}
		return tmp;
	}
	/**
	 * 保存
	 * @param entity
	 */
	public void save(SysUser entity) {
		entity.setExtParams("");

		entity.setCreateBy(CurrentRequestContext.getOpBy());
		entity.setCreateOn(new Date());
		entity.setOpOn(entity.getCreateOn());
		entity.setOpBy(entity.getCreateBy());
//		entity.setPwdSalt("");
		this.checkEntity(entity);
		
		if(StringUtils.isEmpty(entity.getPasswd())) {
			entity.setPasswd(DEFAULT_PWD);
		}
		entity.setPasswd(this.encodePwd(entity.getPasswd()));
		repo.insert(entity);
	}
	
	/**
	 * 修改
	 * @param entity
	 */
	public void update(SysUser entity) {
		Integer logBy = CurrentRequestContext.getOpBy();

		entity.setOpOn(new Date());
		entity.setOpBy(logBy);
		SysUser tmp = this.checkEntity(entity);
		if(!entity.getPasswd().equals(tmp.getPasswd())) {
			entity.setPasswd(this.encodePwd(entity.getPasswd()));
		}
		repo.update(entity);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer pk) {
		repo.deleteByPK(pk);
	}

	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public SysUser findById(Integer pk) {
		return repo.selectByPK(pk);
	}

	/**
	 * 通过非空属性查询
	 * @param SysUser
	 * @return
	 */
	public List<SysUser> findByNotNullProps(SysUser entity) {

		SelectStatement<SysUser> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}

	/**
	 * 通过主键更新非空属性
	 * @param SysUser
	 * @return
	 */
	public void updateNotNullPropsById(SysUser entity) {

		UpdateStatement<SysUser> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}

	//

	@Override
	public List<SysUser> findByQueryParam(SysUser.QueryParam param) {
		return this.repo.findByQueryParam(param);
	}

	@Override
	public long countByQueryParam(SysUser.QueryParam param) {
		return this.repo.countByQueryParam(param);
	}

	private Boolean isUserPasswordTrue(SysUser user, String pwd) {
		return true;
//		return user.getPwd().equals(pwd);
//		byte[] saltBytes = PasswordUtil.hexStringToBytes(user.getPwdSalt());
//		return user.getPwd().equals(PasswordUtil.entryptPassword(pwd, saltBytes));

	}

	@Override
	public void updatePwd(Integer id, String oldPwd, String password) {
		SysUser sysUser = this.findById(id);
		Assert.notNull(sysUser, "用户不存在");
		Assert.isTrue(isUserPasswordTrue(sysUser, oldPwd), "旧密码不正确");

//		byte[] saltBytes = PasswordUtil.hexStringToBytes(sysUser.getPwdSalt());
//		
//		String encodePwd = PasswordUtil.entryptPassword(password, saltBytes);
//		

		SysUser.EntityNode n = SysUser.EntityNode.INSTANCE;
		UpdateStatement<SysUser> st = StatementBuilder.buildUpdate(n);
		st.restrictions().add(n.id.eq(id));
//		st.addField(n.pwd,password);
		st.addField(n.opBy, CurrentRequestContext.getOpBy());
		st.addField(n.opOn, new Date());

		this.repo.updateByStatement(st);
	}

	@Override
	public List<SysResourceGroup> findUserResources(Integer userId) {
		SysUserRole p = new SysUserRole();
		p.setUserId(userId);
		List<SysUserRole> urList = sysUserRoleService.findByNotNullProps(p);
		if (urList != null && urList.size() > 0) {
			List<Integer> roleIdList = new ArrayList<Integer>(urList.size());
			for (SysUserRole ur : urList) {
				roleIdList.add(ur.getRoleId());
			}
			return this.sysRoleService.findRoleResources(roleIdList);
		}
		return null;
	}

	@Override
	public List<Integer> findRoleIds(Integer userId, Integer type) {
		List<SysRole> roles = sysRoleService.findUserRoles(userId, type);
		return roles.stream().map(SysRole::getId).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void assignRoles(Integer userId, Integer roleType, List<Integer> roleIds) {
		Set<Integer> dstRoleIds = new HashSet<Integer>();
		if (roleIds != null) {
			dstRoleIds.addAll(roleIds);
		}

		SysUserRole.QueryParam queryParam = new SysUserRole.QueryParam();
		queryParam.setParam(new SysUserRole());
		queryParam.getParam().setUserId(userId);
		queryParam.setRoleType(roleType);

		List<SysUserRole> oldResources = this.sysUserRoleService.findByQueryParam(queryParam);
		// Set<Integer> delIds = new HashSet<Integer>();
		if (oldResources != null) {
			Iterator<SysUserRole> it = oldResources.iterator();
			while (it.hasNext()) {
				SysUserRole tmp = it.next();
				if (dstRoleIds.contains(tmp.getRoleId())) {
					dstRoleIds.remove(tmp.getRoleId());
				} else {
					// delIds.add(tmp.getId());
					sysUserRoleService.deleteById(tmp.getId());
				}
			}
		}
		if (dstRoleIds.size() > 0) {
			for (Integer roleId : dstRoleIds) {
				SysUserRole sr = new SysUserRole();
				sr.setUserId(userId);
				sr.setRoleId(roleId);
				this.sysUserRoleService.save(sr);
			}
		}

	}

	@Override
	public void assignPositions(Integer userId, List<Integer> positionIds) {
		Set<Integer> dstRoleIds = new HashSet<Integer>();
		if (positionIds != null) {
			dstRoleIds.addAll(positionIds);
		}
		SysUserPosition param = new SysUserPosition();
		param.setUserId(userId);

		List<SysUserPosition> oldResources = this.sysUserPositionService.findByNotNullProps(param);
		// Set<Integer> delIds = new HashSet<Integer>();
		if (oldResources != null) {
			Iterator<SysUserPosition> it = oldResources.iterator();
			while (it.hasNext()) {
				SysUserPosition tmp = it.next();
				if (dstRoleIds.contains(tmp.getPositionId())) {
					dstRoleIds.remove(tmp.getPositionId());
				} else {
					// delIds.add(tmp.getId());
					sysUserPositionService.deleteById(tmp.getId());
				}
			}
		}
		if (dstRoleIds.size() > 0) {
			for (Integer roleId : dstRoleIds) {
				SysUserPosition sr = new SysUserPosition();
				sr.setUserId(userId);
				sr.setPositionId(roleId);
				this.sysUserPositionService.save(sr);
			}
		}

	}

	@Override
	public List<SysPosition> findUserPositions(Integer userId) {
		return this.sysPositionService.findUserPositions(userId);
	}

	@Override
	public List<SysUser> findByIds(List<Integer> userIds) {
		SysUser.EntityNode n = SysUser.EntityNode.INSTANCE;
		SelectStatement<SysUser> st = StatementBuilder.buildSelect(n);
		st.restrictions().add(n.id.in(userIds));
		return this.repo.selectByStatement(st);
	}

	@Override
	public List<SysUser> findProcessUserOfOrg(Integer orgId, String processCode) {
		return this.repo.findProcessUserOfOrg(orgId, processCode);
	}

	@Override
	public SysUser findWithOrgByUserId(Integer userId) {
		return this.repo.findWithOrgByUserId(userId);
	}


	@Override
	public SysUser findByUsername(Integer corpId, String username) {
		SysUser.EntityNode n = SysUser.EntityNode.INSTANCE;
		SelectStatement<SysUser> st = StatementBuilder.buildSelect(n);
		st.restrictions().add(n.corpId.eq(corpId)).add(n.username.eq(username));
		return CollectionUtil.uniqueResult(this.repo.selectByStatement(st));
	}

	public List<SysUser> findByKeywords(Integer corpId,Integer userId, String keywords) {
		List<SysUser> suList = this.repo.findByKeywords(corpId,userId,keywords);
		return suList;
	}

	@Override
	public List<Integer> findUserIdsByRoleCode(Integer corpId, String roleCode) {
		return this.repo.findUserIdsByRoleCode(corpId,roleCode);
	}
	@Override
	public SysUser checkPass(Integer corpId, String username, String password) {
		SysUser su = this.findByUsername(corpId, username);
		if(su == null) {
			throw new BusinessException("account_unvalid","用户不存在");
		}
		if(!encodePwd(password).equals(su.getPasswd())) {
			throw new BusinessException("account_unvalid","密码不正确");
		}
		
		return su;
	}
	
	private String encodePwd(String pwd) {
		return SecurityUtil.md5(pwd).toLowerCase();
	}
	@Override
	public SysUser modifyPass(Integer userId, String oldPass, String newPass) {
		SysUser u = this.findById(userId);
		if(!u.getPasswd().equals(this.encodePwd(oldPass))) {
			throw new BusinessException("account_unvalid","密码不正确");
		}
		u.setPasswd(this.encodePwd(newPass));
		this.updateNotNullPropsById(u);
		return u;
	}
}
