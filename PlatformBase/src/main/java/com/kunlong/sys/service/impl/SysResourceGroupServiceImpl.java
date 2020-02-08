package com.kunlong.sys.service.impl;

import com.kunlong.sys.support.CurrentRequestContext;
import com.kunlong.sys.support.validation.BeanValidator;
import com.kunlong.sys.dao.SysResourceGroupMapper;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.sys.queryParam.SysResourceGroupQueryParam;
import com.kunlong.sys.service.SysResourceGroupService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * SysResourceGroupServiceImpl
 * 
 * @author generator
 * @date 2015年12月05日
 */
@Service
public class SysResourceGroupServiceImpl implements SysResourceGroupService {

	@Autowired
	private SysResourceGroupMapper repo;

	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public void save(SysResourceGroup entity) {
		entity.setCreateBy(CurrentRequestContext.getOpBy());
		entity.setCreateOn(new Date());
		entity.setOpOn(entity.getCreateOn());
		entity.setOpBy(entity.getCreateBy());
		this.checkEntity(entity);
		repo.insert(entity);
		
	}

	private void checkEntity(SysResourceGroup entity) {
		BeanValidator.propertyCheck(entity);
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	public void update(SysResourceGroup entity) {
		entity.setOpBy(CurrentRequestContext.getOpBy());
		
		entity.setOpOn(entity.getCreateOn());
		this.checkEntity(entity);
		repo.update(entity);
		
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void deleteById(Integer pk) {
		repo.deleteByPK(pk);
	}

	/**
	 * 通过id获取
	 * 
	 * @param id
	 * @return
	 */
	public SysResourceGroup findById(Integer pk) {
		return repo.selectByPK(pk);
	}

	/**
	 * 通过非空属性查询
	 * 
	 * @param SysResourceGroup
	 * @return
	 */
	public List<SysResourceGroup> findByNotNullProps(SysResourceGroup entity) {

		SelectStatement<SysResourceGroup> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}

	/**
	 * 通过主键更新非空属性
	 * 
	 * @param SysResourceGroup
	 * @return
	 */
	public void updateNotNullPropsById(SysResourceGroup entity) {

		UpdateStatement<SysResourceGroup> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}

	@Override
	public List<SysResourceGroup> findAllByType(Integer domainId,Byte resType) {
		return this.repo.findAllByType(domainId,resType);
	}

	private SelectStatement<SysResourceGroup> createStatement(SysResourceGroupQueryParam queryParam) {
		SysResourceGroup.EntityNode n = SysResourceGroup.EntityNode.INSTANCE;

		SelectStatement<SysResourceGroup> st = StatementBuilder.buildSelect(n);
		SysResourceGroup p = queryParam.getParam();
		if (p != null) {
			if (p.getDomainId() != null) {
				st.restrictions().add(n.domainId.eq(p.getDomainId()));
			}
			if (p.getGroupType() != null) {
				st.restrictions().add(n.groupType.eq(p.getGroupType()));
			}
			if (!StringUtils.isEmpty(p.getGroupName())) {
				st.restrictions().add(n.groupName.like("%" + p.getGroupName() + "%"));
			}

		}
		if (queryParam.getStart() != null && queryParam.getStart() > -1) {
			st.setPageRange(queryParam.getStart(), queryParam.getLimit());
		}
		st.orderby().desc(n.opOn);
		return st;
	}

	@Override
	public List<SysResourceGroup> findByQueryParam(SysResourceGroupQueryParam queryParam) {
		SelectStatement<SysResourceGroup> st = this.createStatement(queryParam);
		return this.repo.selectByStatement(st);
	}
	
	@Override
	public List<SysResourceGroup> findRoleResources(List<Integer> roleIds) {
		return this.repo.findRoleResources(roleIds);
	}

	@Override
	public List<SysResourceGroup> findByIds(List<Integer> ids) {
		SysResourceGroup.EntityNode n = SysResourceGroup.EntityNode.INSTANCE;
		SelectStatement<SysResourceGroup> st = SelectStatement.newInstance(n);
		st.getRestrictions().add(n.id.in(ids));
		st.orderby().asc(n.orderNum);
		return this.repo.selectByStatement(st);
	}
}
