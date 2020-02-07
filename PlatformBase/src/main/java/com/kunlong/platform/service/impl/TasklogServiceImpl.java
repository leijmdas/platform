package com.kunlong.platform.service.impl;

import java.util.List;

import com.kunlong.platform.dao.TasklogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.service.TasklogService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * TasklogServiceImpl
 * @author generator
 * @date 2020年01月30日
 */
@Service
public class TasklogServiceImpl implements TasklogService {
	
	@Autowired
	private TasklogMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	public void save(Tasklog entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void update(Tasklog entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(Tasklog entity){
	
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
	public Tasklog findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param Tasklog
	 * @return
	 */
	public List<Tasklog> findByNotNullProps(Tasklog entity){
		
		SelectStatement<Tasklog> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param Tasklog
	 * @return
	 */
	public void updateNotNullPropsById(Tasklog entity){
		
		UpdateStatement<Tasklog> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param Tasklog.QueryParam
	 * @return
	 */
	public List<Tasklog> findByQueryParam(Tasklog.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param Tasklog.QueryParam
	 * @return
	 */
	public long countByQueryParam(Tasklog.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<Tasklog> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<Tasklog> items, Tasklog.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
