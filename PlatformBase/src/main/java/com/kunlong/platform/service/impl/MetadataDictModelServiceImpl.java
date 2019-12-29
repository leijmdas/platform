package com.kunlong.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.dao.MetadataDictModelMapper;
import com.kunlong.platform.service.MetadataDictModelService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * MetadataDictModelServiceImpl
 * @author generator
 * @date 2019年12月29日
 */
@Service
public class MetadataDictModelServiceImpl implements MetadataDictModelService {
	
	@Autowired
	private MetadataDictModelMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MetadataDictModel entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void update(MetadataDictModel entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(MetadataDictModel entity){
	
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
	public MetadataDictModel findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param MetadataDictModel
	 * @return
	 */
	public List<MetadataDictModel> findByNotNullProps(MetadataDictModel entity){
		
		SelectStatement<MetadataDictModel> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param MetadataDictModel
	 * @return
	 */
	public void updateNotNullPropsById(MetadataDictModel entity){
		
		UpdateStatement<MetadataDictModel> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param MetadataDictModel.QueryParam
	 * @return
	 */
	public List<MetadataDictModel> findByQueryParam(MetadataDictModel.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param MetadataDictModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(MetadataDictModel.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<MetadataDictModel> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<MetadataDictModel> items, MetadataDictModel.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
