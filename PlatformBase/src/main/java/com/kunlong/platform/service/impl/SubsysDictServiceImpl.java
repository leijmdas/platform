package com.kunlong.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.dao.SubsysDictMapper;
import com.kunlong.platform.service.SubsysDictService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * SubsysDictServiceImpl
 * @author generator
 * @date 2019年12月29日
 */
@Service
public class SubsysDictServiceImpl implements SubsysDictService {
	
	@Autowired
	private SubsysDictMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	@CachePut("${cache.name}")
	public void save(SubsysDict entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	@CachePut("${cache.name}")
	public void update(SubsysDict entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(SubsysDict entity){
	
	}
	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(Integer pk){
		repo.deleteByPK(pk);
	}
	
	/**
	 * 通过id获取
	 * @param id
	 * @return
	 */
	public SubsysDict findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param SubsysDict
	 * @return
	 */
	public List<SubsysDict> findByNotNullProps(SubsysDict entity){
		
		SelectStatement<SubsysDict> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param SubsysDict
	 * @return
	 */
	public void updateNotNullPropsById(SubsysDict entity){
		
		UpdateStatement<SubsysDict> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param SubsysDict.QueryParam
	 * @return
	 */
	@Cacheable("${cache.name}")
	public List<SubsysDict> findByQueryParam(SubsysDict.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param SubsysDict.QueryParam
	 * @return
	 */
	public long countByQueryParam(SubsysDict.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<SubsysDict> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<SubsysDict> items, SubsysDict.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
