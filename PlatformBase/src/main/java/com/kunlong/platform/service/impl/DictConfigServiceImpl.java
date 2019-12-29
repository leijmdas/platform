package com.kunlong.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.DictConfig;
import com.kunlong.platform.dao.DictConfigMapper;
import com.kunlong.platform.service.DictConfigService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * DictConfigServiceImpl
 * @author generator
 * @date 2019年12月29日
 */
@Service
public class DictConfigServiceImpl implements DictConfigService {
	
	@Autowired
	private DictConfigMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	public void save(DictConfig entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void update(DictConfig entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(DictConfig entity){
	
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
	public DictConfig findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param DictConfig
	 * @return
	 */
	public List<DictConfig> findByNotNullProps(DictConfig entity){
		
		SelectStatement<DictConfig> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param DictConfig
	 * @return
	 */
	public void updateNotNullPropsById(DictConfig entity){
		
		UpdateStatement<DictConfig> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param DictConfig.QueryParam
	 * @return
	 */
	public List<DictConfig> findByQueryParam(DictConfig.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param DictConfig.QueryParam
	 * @return
	 */
	public long countByQueryParam(DictConfig.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<DictConfig> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<DictConfig> items, DictConfig.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
