package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.validation.constraints.*;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;
/**
 * DictDataModel 数据字典
 * @author generator
 * @date 2020年02月20日
 */
@Table(DictDataModel.EntityNode.class)
public class DictDataModel extends DictDataModelBase implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 字典ID
	  * nullable:true,length:11
	  */
	@Column(primaryKey = true,autoIncrement = true,comment = "字典ID")	
	private Integer id;
	/**
	  * 分类编号
	  * nullable:true,length:11
	  */
	@Column(comment = "分类编号")	
	private Integer typeCode;
	/**
	  * 分类名称
	  * nullable:true,length:32
	  */
	@Column(comment = "分类名称")	
	private String typeName;
	/**
	  * 字典编号
	  * nullable:true,length:11
	  */
	@Column(comment = "字典编号")	
	private Integer code;
	/**
	  * 字典名称
	  * nullable:true,length:32
	  */
	@Column(comment = "字典名称")	
	private String name;
	/**
	  * 创建时间
	  * nullable:false,length:19
	  */
	@Column(comment = "创建时间")	
	@NotNull
	private Date createTime;
	/**
	  * 创建人
	  * nullable:true,length:11
	  */
	@Column(comment = "创建人")	
	private Integer createBy;
	/**
	  * 备注
	  * nullable:true,length:64
	  */
	@Column(comment = "备注")	
	private String remark;
	/**
	  * 
	  * nullable:false,length:11
	  */
	@Column(comment = "")	
	@NotNull
	private Integer subsysId;
    public Integer getId(){
    	return this.id;
    }
    public void setId(Integer id){
    	this.id = id;
    }
    public Integer getTypeCode(){
    	return this.typeCode;
    }
    public void setTypeCode(Integer typeCode){
    	this.typeCode = typeCode;
    }
    public String getTypeName(){
    	return this.typeName;
    }
    public void setTypeName(String typeName){
    	this.typeName = typeName;
    }
    public Integer getCode(){
    	return this.code;
    }
    public void setCode(Integer code){
    	this.code = code;
    }
    public String getName(){
    	return this.name;
    }
    public void setName(String name){
    	this.name = name;
    }
    public Date getCreateTime(){
    	return this.createTime;
    }
    public void setCreateTime(Date createTime){
    	this.createTime = createTime;
    }
    public Integer getCreateBy(){
    	return this.createBy;
    }
    public void setCreateBy(Integer createBy){
    	this.createBy = createBy;
    }
    public String getRemark(){
    	return this.remark;
    }
    public void setRemark(String remark){
    	this.remark = remark;
    }
    public Integer getSubsysId(){
    	return this.subsysId;
    }
    public void setSubsysId(Integer subsysId){
    	this.subsysId = subsysId;
    }

    public static class EntityNode extends AbstractEntityNode<DictDataModel> {
        public static final EntityNode INSTANCE = new EntityNode("dd");;
    	/** 字典ID */
        public FieldNode<DictDataModel, Integer> id =  createFieldNode("id","id",Integer.class,JdbcType.INTEGER);
    	/** 分类编号 */
        public FieldNode<DictDataModel, Integer> typeCode =  createFieldNode("typeCode","type_code",Integer.class,JdbcType.INTEGER);
    	/** 分类名称 */
        public FieldNode<DictDataModel, String> typeName =  createFieldNode("typeName","type_name",String.class,JdbcType.VARCHAR);
    	/** 字典编号 */
        public FieldNode<DictDataModel, Integer> code =  createFieldNode("code","code",Integer.class,JdbcType.INTEGER);
    	/** 字典名称 */
        public FieldNode<DictDataModel, String> name =  createFieldNode("name","name",String.class,JdbcType.VARCHAR);
    	/** 创建时间 */
        public FieldNode<DictDataModel, Date> createTime =  createFieldNode("createTime","create_time",Date.class,JdbcType.TIMESTAMP);
    	/** 创建人 */
        public FieldNode<DictDataModel, Integer> createBy =  createFieldNode("createBy","create_by",Integer.class,JdbcType.INTEGER);
    	/** 备注 */
        public FieldNode<DictDataModel, String> remark =  createFieldNode("remark","remark",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<DictDataModel, Integer> subsysId =  createFieldNode("subsysId","subsys_id",Integer.class,JdbcType.INTEGER);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(DictDataModel.class,"dict_data",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<DictDataModel> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<DictDataModel>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}