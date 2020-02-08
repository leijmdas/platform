package com.kunlong.sys.service.impl;

import com.kunlong.sys.support.CurrentRequestContext;
import com.kunlong.sys.dao.SysPositionMapper;
import com.kunlong.sys.dao.SysResourceGroupMapper;
import com.kunlong.sys.domain.SysPosition;
import com.kunlong.sys.domain.SysPositionResource;
import com.kunlong.sys.domain.SysResource;
import com.kunlong.sys.domain.SysResourceGroup;
import com.kunlong.sys.queryParam.SysPositionQueryParam;
import com.kunlong.sys.service.SysPositionResourceService;
import com.kunlong.sys.service.SysPositionService;
import com.kunlong.core.enums.StatusEnum;
import com.kunlong.core.util.CollectionUtil;
import org.mybatis.hbatis.orm.criteria.Restrictions;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.validation.ValidationException;
import java.util.*;

/**
 * SysPositionServiceImpl
 * 
 * @author generator
 * @date 2015年12月05日
 */
@Service
public class SysPositionServiceImpl implements SysPositionService {

	@Autowired
	private SysPositionMapper repo;
	
	@Autowired
	private SysResourceGroupMapper groupRepo;

	@Autowired
	private SysPositionResourceService sysPositionResourceService;
	/**
	 * 保存
	 * 
	 * @param entity
	 */
	public void save(SysPosition entity) {
		entity.setOpBy(CurrentRequestContext.getOpBy());
		entity.setOpOn(new Date());
		entity.setCreateBy(CurrentRequestContext.getOpBy());
		entity.setCreateOn(new Date());
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * 
	 * @param entity
	 */
	@Transactional
	public void update(SysPosition entity) {
		entity.setOpBy(CurrentRequestContext.getOpBy());
		entity.setOpOn(new Date());
		this.checkEntity(entity);

		repo.update(entity);

	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@Transactional
	public void deleteById(Integer pk) {
		SysPosition.EntityNode n = SysPosition.EntityNode.INSTANCE;
		Restrictions<SysPosition> rs = StatementBuilder.buildRestrictions(n);
		rs.add(n.id.eq(pk));
		this.repo.deleteByRestrictions(rs);
	}

	/**
	 * 通过id获取
	 * 
	 * @param id
	 * @return
	 */
	public SysPosition findById(Integer pk) {
		return repo.selectByPK(pk);
	}

	/**
	 * 通过非空属性查询
	 * 
	 * @param SysPosition
	 * @return
	 */
	public List<SysPosition> findByNotNullProps(SysPosition entity) {

		SelectStatement<SysPosition> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}

	/**
	 * 通过主键更新非空属性
	 * 
	 * @param SysPosition
	 * @return
	 */
	public void updateNotNullPropsById(SysPosition entity) {

		UpdateStatement<SysPosition> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}

	@Override
	public List<SysPosition> findAllAvaliable() {
		SysPosition.EntityNode n = SysPosition.EntityNode.INSTANCE;
		SelectStatement<SysPosition> st = StatementBuilder.buildSelect(n);
		st.restrictions().add(n.status.eq(StatusEnum.ENABLE.getValue()));
		return this.repo.selectByStatement(st);
	}

	@Override
	public List<SysPosition> findByQueryParam(SysPositionQueryParam queryParam) {
		return this.repo.findByQueryParam(queryParam);
	}

	@Override
	public long countByQueryParam(SysPositionQueryParam queryParam) {
		return this.repo.countByQueryParam(queryParam);
	}

	private void checkEntity(SysPosition entity) {

		SysPosition tmp = this.findByPidAndName(entity.getOrgId(), entity.getPositionName());
		if (tmp != null && !tmp.getId().equals(entity.getId())) {
			throw new ValidationException("同层级下名称已存在[名称:" + entity.getPositionName() + "]");
		}
	}

	private SysPosition findByPidAndName(Integer orgId, String name) {
		SysPosition.EntityNode n = SysPosition.EntityNode.INSTANCE;
		SelectStatement<SysPosition> st = StatementBuilder.buildSelect(n);
		st.restrictions().add(n.orgId.eq(orgId)).add(n.positionName.eq(name));
		return CollectionUtil.uniqueResult(this.repo.selectByStatement(st));
	}

	@Override
	public List<SysPosition> findCascadePositionsByOrgId(Integer orgId) {
		return this.repo.findCascadePositionsByOrgId(orgId);
	}

	@Override
	public List<SysPosition> findUserPositions(Integer userId) {
		return this.repo.findUserPositions(userId);
	}

	@Transactional
	@Override
	public void assignPositionResources(Integer positionId, List<Integer> resIds) {
		Set<Integer> dstResIds = new HashSet<Integer>();
		if (resIds != null) {
			dstResIds.addAll(resIds);
		}
		SysPositionResource param = new SysPositionResource();
		param.setPositionId(positionId);

		List<SysPositionResource> oldResources = this.sysPositionResourceService.findByNotNullProps(param);
		// Set<Integer> delIds = new HashSet<Integer>();
		if (oldResources != null) {
			Iterator<SysPositionResource> it = oldResources.iterator();
			while (it.hasNext()) {
				SysPositionResource tmp = it.next();
				if (dstResIds.contains(tmp.getResourceId())) {
					dstResIds.remove(tmp.getResourceId());
				} else {
					// delIds.add(tmp.getId());
					this.sysPositionResourceService.deleteById(tmp.getId());
				}
			}
		}
		if (dstResIds.size() > 0) {
			for (Integer id : dstResIds) {
				SysPositionResource sr = new SysPositionResource();
				sr.setResourceId(id);
				sr.setPositionId(positionId);
				this.sysPositionResourceService.save(sr);
			}
		}

	}

	@Override
	public List<SysResourceGroup> findPositionResources(List<Integer> positionIds) {
		Assert.isTrue(positionIds != null && positionIds.size() > 0, "集合元素不能为空");
		return this.groupRepo.findPositionResources(positionIds);
	}

	@Override
	public List<SysResource> findPositionResources(Integer positionId) {
		List<SysResourceGroup> resGroupList = this.findPositionResources(Arrays.asList(positionId));
		List<SysResource> rs = new ArrayList<SysResource>();
		for (SysResourceGroup resGroup : resGroupList) {
			if (resGroup.getSysResources() != null) {
				rs.addAll(resGroup.getSysResources());
			}
		}
		return rs;
	}

}
