package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.String;
import java.lang.Short;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;
/**
 * SubsysDict 子系统表
 * @author generator
 * @date 2019年12月29日
 */
@Table(SubsysDict.EntityNode.class)
public class SubsysDict implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 子系统标识号
	  * nullable:true,length:11
	  */
	@Column(primaryKey = true,autoIncrement = true,comment = "子系统标识号")	
	private Integer subsysId;
	/**
	  * 
	  * nullable:true,length:64
	  */
	@Column(comment = "")	
	private String subsysNo;
	/**
	  * 子系统名称
	  * nullable:false,length:64
	  */
	@Column(comment = "子系统名称")	
	@NotNull
	private String subsysName;
	/**
	  * 子系统
	  * nullable:true,length:64
	  */
	@Column(comment = "子系统")	
	private String remark;
	/**
	  * 
	  * nullable:true,length:64
	  */
	@Column(comment = "")	
	private String ip;
	/**
	  * 
	  * nullable:true,length:2
	  */
	@Column(comment = "")	
	private Short port;
    public Integer getSubsysId(){
    	return this.subsysId;
    }
    public void setSubsysId(Integer subsysId){
    	this.subsysId = subsysId;
    }
    public String getSubsysNo(){
    	return this.subsysNo;
    }
    public void setSubsysNo(String subsysNo){
    	this.subsysNo = subsysNo;
    }
    public String getSubsysName(){
    	return this.subsysName;
    }
    public void setSubsysName(String subsysName){
    	this.subsysName = subsysName;
    }
    public String getRemark(){
    	return this.remark;
    }
    public void setRemark(String remark){
    	this.remark = remark;
    }
    public String getIp(){
    	return this.ip;
    }
    public void setIp(String ip){
    	this.ip = ip;
    }
    public Short getPort(){
    	return this.port;
    }
    public void setPort(Short port){
    	this.port = port;
    }

    public static class EntityNode extends AbstractEntityNode<SubsysDict> {
        public static final EntityNode INSTANCE = new EntityNode("sd");;
    	/** 子系统标识号 */
        public FieldNode<SubsysDict, Integer> subsysId =  createFieldNode("subsysId","subsys_id",Integer.class,JdbcType.INTEGER);
    	/**  */
        public FieldNode<SubsysDict, String> subsysNo =  createFieldNode("subsysNo","subsys_no",String.class,JdbcType.VARCHAR);
    	/** 子系统名称 */
        public FieldNode<SubsysDict, String> subsysName =  createFieldNode("subsysName","subsys_name",String.class,JdbcType.VARCHAR);
    	/** 子系统 */
        public FieldNode<SubsysDict, String> remark =  createFieldNode("remark","remark",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<SubsysDict, String> ip =  createFieldNode("ip","ip",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<SubsysDict, Short> port =  createFieldNode("port","port",Short.class,JdbcType.SMALLINT);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(SubsysDict.class,"subsys_dict",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<SubsysDict> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<SubsysDict>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}