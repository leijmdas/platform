package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.String;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;
/**
 * DictConfig 
 * @author generator
 * @date 2019年12月29日
 */
@Table(DictConfig.EntityNode.class)
public class DictConfig implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@Column(primaryKey = true,autoIncrement = true,comment = "")	
	private Integer configId;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@Column(comment = "")	
	private Integer configType;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@Column(comment = "")	
	@NotNull
	private String configItem;
	/**
	  * 
	  * nullable:true,length:256
	  */
	@Column(comment = "")	
	private String configRemark;
	/**
	  * 
	  * nullable:true,length:256
	  */
	@Column(comment = "")	
	private String configValue;
    public Integer getConfigId(){
    	return this.configId;
    }
    public void setConfigId(Integer configId){
    	this.configId = configId;
    }
    public Integer getConfigType(){
    	return this.configType;
    }
    public void setConfigType(Integer configType){
    	this.configType = configType;
    }
    public String getConfigItem(){
    	return this.configItem;
    }
    public void setConfigItem(String configItem){
    	this.configItem = configItem;
    }
    public String getConfigRemark(){
    	return this.configRemark;
    }
    public void setConfigRemark(String configRemark){
    	this.configRemark = configRemark;
    }
    public String getConfigValue(){
    	return this.configValue;
    }
    public void setConfigValue(String configValue){
    	this.configValue = configValue;
    }

    public static class EntityNode extends AbstractEntityNode<DictConfig> {
        public static final EntityNode INSTANCE = new EntityNode("dc");;
    	/**  */
        public FieldNode<DictConfig, Integer> configId =  createFieldNode("configId","config_id",Integer.class,JdbcType.INTEGER);
    	/**  */
        public FieldNode<DictConfig, Integer> configType =  createFieldNode("configType","config_type",Integer.class,JdbcType.INTEGER);
    	/**  */
        public FieldNode<DictConfig, String> configItem =  createFieldNode("configItem","config_item",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<DictConfig, String> configRemark =  createFieldNode("configRemark","config_remark",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<DictConfig, String> configValue =  createFieldNode("configValue","config_value",String.class,JdbcType.VARCHAR);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(DictConfig.class,"dict_config",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<DictConfig> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<DictConfig>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}