package com.kunlong.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.dao.MetadataFieldModelMapper;
import com.kunlong.platform.service.MetadataFieldModelService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * MetadataFieldModelServiceImpl
 * @author generator
 * @date 2019年12月29日
 */
@Service
public class MetadataFieldModelServiceImpl implements MetadataFieldModelService {
	
	@Autowired
	private MetadataFieldModelMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	public void save(MetadataFieldModel entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void update(MetadataFieldModel entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(MetadataFieldModel entity){
	
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
	public MetadataFieldModel findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param MetadataFieldModel
	 * @return
	 */
	public List<MetadataFieldModel> findByNotNullProps(MetadataFieldModel entity){
		
		SelectStatement<MetadataFieldModel> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param MetadataFieldModel
	 * @return
	 */
	public void updateNotNullPropsById(MetadataFieldModel entity){
		
		UpdateStatement<MetadataFieldModel> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param MetadataFieldModel.QueryParam
	 * @return
	 */
	public List<MetadataFieldModel> findByQueryParam(MetadataFieldModel.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param MetadataFieldModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(MetadataFieldModel.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<MetadataFieldModel> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<MetadataFieldModel> items, MetadataFieldModel.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
