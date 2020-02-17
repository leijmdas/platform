//package com.kunlong.platform.service.impl;
//
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import com.kunlong.platform.service.MetadataFieldService;
//import org.mybatis.hbatis.orm.criteria.statement.SelectStatement;
//import org.mybatis.hbatis.orm.criteria.statement.UpdateStatement;
//import org.mybatis.hbatis.orm.criteria.support.StatementBuilder;
///**
// * MetadataFieldServiceImpl
// * @author generator
// * @date 2020年02月17日
// */
//@Service
//public class MetadataFieldServiceImpl implements MetadataFieldService {
//
//	@Autowired
//	private MetadataFieldMapper repo;
//	/**
//	 * 保存
//	 * @param entity
//	 */
//	public void save(MetadataField entity){
//		this.checkEntity(entity);
//		repo.insert(entity);
//	}
//
//	/**
//	 * 修改
//	 * @param entity
//	 */
//	public void update(MetadataField entity){
//		this.checkEntity(entity);
//		repo.update(entity);
//	}
//	/**
//	 * 较验实体
//	 * @param entity
//	 */
//	public void checkEntity(MetadataField entity){
//
//	}
//	/**
//	 * 删除
//	 * @param id
//	 */
//	public void deleteById(Integer pk){
//		repo.deleteByPK(pk);
//	}
//
//	/**
//	 * 通过id获取
//	 * @param id
//	 * @return
//	 */
//	public MetadataField findById(Integer pk){
//		return repo.selectByPK(pk);
//	}
//	/**
//	 * 通过非空属性查询
//	 * @param MetadataField
//	 * @return
//	 */
//	public List<MetadataField> findByNotNullProps(MetadataField entity){
//
//		SelectStatement<MetadataField> st = StatementBuilder.buildSelectSelective(entity);
//		return repo.selectByStatement(st);
//	}
//	/**
//	 * 通过主键更新非空属性
//	 * @param MetadataField
//	 * @return
//	 */
//	public void updateNotNullPropsById(MetadataField entity){
//
//		UpdateStatement<MetadataField> st = StatementBuilder.buildUpdateSelective(entity);
//		repo.updateByStatement(st);
//	}
//
//	/**
//	 * 通过实体参数分页查询
//	 * @param MetadataField.QueryParam
//	 * @return
//	 */
//	public List<MetadataField> findByQueryParam(MetadataField.QueryParam queryParam){
//		return repo.findByQueryParam(queryParam);
//	}
//	/**
//	 * 通过实体参数统计
//	 * @param MetadataField.QueryParam
//	 * @return
//	 */
//	public long countByQueryParam(MetadataField.QueryParam queryParam) {
//		return repo.countByQueryParam(queryParam);
//	}
//	/**
//	 * 通过ID集合查询
//	 * @param List<Integer> pks
//	 * @return
//	 */
//	public List<MetadataField> findByIds(List<Integer> pks) {
//		return repo.selectByPKS(pks);
//	}
//	/**
//	 * 值填充
//	 */
//	public void fillValues(List<MetadataField> items, MetadataField.ValueField... field) {
//		if (items == null || items.isEmpty() || field.length < 1) {
//			return;
//		}
//	}
//}
