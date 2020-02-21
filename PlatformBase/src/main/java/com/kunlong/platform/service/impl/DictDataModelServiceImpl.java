package com.kunlong.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.kunlong.platform.domain.DictDataModel;
import com.kunlong.platform.dao.DictDataModelMapper;
import com.kunlong.platform.service.DictDataModelService;
import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
/**
 * DictDataModelServiceImpl
 * @author generator
 * @date 2020年02月20日
 */
@Service
public class DictDataModelServiceImpl implements DictDataModelService {
	
	@Autowired
	private DictDataModelMapper repo;
	/**
	 * 保存
	 * @param entity
	 */
	public void save(DictDataModel entity){
		this.checkEntity(entity);
		repo.insert(entity);
	}

	/**
	 * 修改
	 * @param entity
	 */
	public void update(DictDataModel entity){
		this.checkEntity(entity);
		repo.update(entity);
	}
	/**
	 * 较验实体
	 * @param entity
	 */
	public void checkEntity(DictDataModel entity){
	
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
	public DictDataModel findById(Integer pk){
		return repo.selectByPK(pk);
	}
	/**
	 * 通过非空属性查询
	 * @param DictDataModel
	 * @return
	 */
	public List<DictDataModel> findByNotNullProps(DictDataModel entity){
		
		SelectStatement<DictDataModel> st = StatementBuilder.buildSelectSelective(entity);
		return repo.selectByStatement(st);
	}
	/**
	 * 通过主键更新非空属性
	 * @param DictDataModel
	 * @return
	 */
	public void updateNotNullPropsById(DictDataModel entity){
		
		UpdateStatement<DictDataModel> st = StatementBuilder.buildUpdateSelective(entity);
		repo.updateByStatement(st);
	}
	
	/**
	 * 通过实体参数分页查询
	 * @param DictDataModel.QueryParam
	 * @return
	 */
	public List<DictDataModel> findByQueryParam(DictDataModel.QueryParam queryParam){
		return repo.findByQueryParam(queryParam);
	}
	/**
	 * 通过实体参数统计
	 * @param DictDataModel.QueryParam
	 * @return
	 */
	public long countByQueryParam(DictDataModel.QueryParam queryParam) {
		return repo.countByQueryParam(queryParam);
	}
	/**
	 * 通过ID集合查询
	 * @param List<Integer> pks
	 * @return
	 */
	public List<DictDataModel> findByIds(List<Integer> pks) {
		return repo.selectByPKS(pks);
	}
	/**
	 * 值填充
	 */
	public void fillValues(List<DictDataModel> items, DictDataModel.ValueField... field) {
		if (items == null || items.isEmpty() || field.length < 1) {
			return;
		}
	}
}
