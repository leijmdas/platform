package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.kunlong.platform.model.KunlongModel;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.String;
import java.lang.Boolean;
import java.lang.Byte;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;
/**
 * MetadataDictModel 元数据主表
 * @author generator
 * @date 2019年12月29日
 */
@Table(MetadataDictModel.EntityNode.class)
public class MetadataDictModel extends KunlongModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 元数据标识号
	  * nullable:true,length:11
	  */
	@Column(primaryKey = true,autoIncrement = true,comment = "元数据标识号")	
	private Integer metadataId;
	/**
	  * 子系统标识号
	  * nullable:false,length:11
	  */
	@Column(comment = "子系统标识号")	
	@NotNull
	private Integer subsysId;
	/**
	  * 排序方式
	  * nullable:false,length:11
	  */
	@Column(comment = "排序方式")	
	@NotNull
	private Integer metadataOrder;
	/**
	  * 元数据名称
	  * nullable:false,length:64
	  */
	@Column(comment = "元数据名称")	
	@NotNull
	private String metadataName;
	/**
	  * 元数据别名
	  * nullable:false,length:64
	  */
	@Column(comment = "元数据别名")	
	@NotNull
	private String metadataAlias;
	/**
	  * 元数据描述
	  * nullable:true,length:128
	  */
	@Column(comment = "元数据描述")	
	private String metadataMemo;
	/**
	  * 关联元数据ID
	  * nullable:false,length:11
	  */
	@Column(comment = "关联元数据ID")	
	@NotNull
	private Integer metadataParentid;
	/**
	  * 元数据类型
	  * nullable:false,length:11
	  */
	@Column(comment = "元数据类型")	
	@NotNull
	private Integer metadataType;
	/**
	  * 表记录是否缓存
	  * nullable:true,length:1
	  */
	@Column(comment = "表记录是否缓存")	
	private Boolean metadataCached;
	/**
	  * 归属数据库
	  * nullable:false,length:32
	  */
	@Column(comment = "归属数据库")	
	@NotNull
	private String metadataDb;
	/**
	  * 是否auto创建
	  * nullable:false,length:1
	  */
	@Column(comment = "是否auto创建")	
	@NotNull
	private Boolean metadataAutocreate;
	/**
	  * 脚本
	  * nullable:true,length:1,024
	  */
	@Column(comment = "脚本")	
	private String metadataStmt;
	/**
	  * 唯一索引字段
	  * nullable:true,length:256
	  */
	@Column(comment = "唯一索引字段")	
	private String metadataIndex;
	/**
	  * 说明
	  * nullable:true,length:1,024
	  */
	@Column(comment = "说明")	
	private String metadataRemark;
	/**
	  * 
	  * nullable:false,length:256
	  */
	@Column(comment = "")	
	@NotNull
	private String metadataSortFields;
	/**
	  * 
	  * nullable:false,length:4
	  */
	@Column(comment = "")	
	@NotNull
	private Byte refSrc;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@Column(comment = "")	
	@NotNull
	private String refObject;
	/**
	  * 
	  * nullable:false,length:256
	  */
	@Column(comment = "")	
	@NotNull
	private String refParam;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@Column(comment = "")	
	@NotNull
	private Boolean metadataReadonly;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@Column(comment = "")	
	@NotNull
	private Boolean metadataAddDel;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@Column(comment = "")	
	@NotNull
	private Boolean expTagtableHead;
    public Integer getMetadataId(){
    	return this.metadataId;
    }
    public void setMetadataId(Integer metadataId){
    	this.metadataId = metadataId;
    }
    public Integer getSubsysId(){
    	return this.subsysId;
    }
    public void setSubsysId(Integer subsysId){
    	this.subsysId = subsysId;
    }
    public Integer getMetadataOrder(){
    	return this.metadataOrder;
    }
    public void setMetadataOrder(Integer metadataOrder){
    	this.metadataOrder = metadataOrder;
    }
    public String getMetadataName(){
    	return this.metadataName;
    }
    public void setMetadataName(String metadataName){
    	this.metadataName = metadataName;
    }
    public String getMetadataAlias(){
    	return this.metadataAlias;
    }
    public void setMetadataAlias(String metadataAlias){
    	this.metadataAlias = metadataAlias;
    }
    public String getMetadataMemo(){
    	return this.metadataMemo;
    }
    public void setMetadataMemo(String metadataMemo){
    	this.metadataMemo = metadataMemo;
    }
    public Integer getMetadataParentid(){
    	return this.metadataParentid;
    }
    public void setMetadataParentid(Integer metadataParentid){
    	this.metadataParentid = metadataParentid;
    }
    public Integer getMetadataType(){
    	return this.metadataType;
    }
    public void setMetadataType(Integer metadataType){
    	this.metadataType = metadataType;
    }
    public Boolean getMetadataCached(){
    	return this.metadataCached;
    }
    public void setMetadataCached(Boolean metadataCached){
    	this.metadataCached = metadataCached;
    }
    public String getMetadataDb(){
    	return this.metadataDb;
    }
    public void setMetadataDb(String metadataDb){
    	this.metadataDb = metadataDb;
    }
    public Boolean getMetadataAutocreate(){
    	return this.metadataAutocreate;
    }
    public void setMetadataAutocreate(Boolean metadataAutocreate){
    	this.metadataAutocreate = metadataAutocreate;
    }
    public String getMetadataStmt(){
    	return this.metadataStmt;
    }
    public void setMetadataStmt(String metadataStmt){
    	this.metadataStmt = metadataStmt;
    }
    public String getMetadataIndex(){
    	return this.metadataIndex;
    }
    public void setMetadataIndex(String metadataIndex){
    	this.metadataIndex = metadataIndex;
    }
    public String getMetadataRemark(){
    	return this.metadataRemark;
    }
    public void setMetadataRemark(String metadataRemark){
    	this.metadataRemark = metadataRemark;
    }
    public String getMetadataSortFields(){
    	return this.metadataSortFields;
    }
    public void setMetadataSortFields(String metadataSortFields){
    	this.metadataSortFields = metadataSortFields;
    }
    public Byte getRefSrc(){
    	return this.refSrc;
    }
    public void setRefSrc(Byte refSrc){
    	this.refSrc = refSrc;
    }
    public String getRefObject(){
    	return this.refObject;
    }
    public void setRefObject(String refObject){
    	this.refObject = refObject;
    }
    public String getRefParam(){
    	return this.refParam;
    }
    public void setRefParam(String refParam){
    	this.refParam = refParam;
    }
    public Boolean getMetadataReadonly(){
    	return this.metadataReadonly;
    }
    public void setMetadataReadonly(Boolean metadataReadonly){
    	this.metadataReadonly = metadataReadonly;
    }
    public Boolean getMetadataAddDel(){
    	return this.metadataAddDel;
    }
    public void setMetadataAddDel(Boolean metadataAddDel){
    	this.metadataAddDel = metadataAddDel;
    }
    public Boolean getExpTagtableHead(){
    	return this.expTagtableHead;
    }
    public void setExpTagtableHead(Boolean expTagtableHead){
    	this.expTagtableHead = expTagtableHead;
    }

    public static class EntityNode extends AbstractEntityNode<MetadataDictModel> {
        public static final EntityNode INSTANCE = new EntityNode("md");;
    	/** 元数据标识号 */
        public FieldNode<MetadataDictModel, Integer> metadataId =  createFieldNode("metadataId","metadata_id",Integer.class,JdbcType.INTEGER);
    	/** 子系统标识号 */
        public FieldNode<MetadataDictModel, Integer> subsysId =  createFieldNode("subsysId","subsys_id",Integer.class,JdbcType.INTEGER);
    	/** 排序方式 */
        public FieldNode<MetadataDictModel, Integer> metadataOrder =  createFieldNode("metadataOrder","metadata_order",Integer.class,JdbcType.INTEGER);
    	/** 元数据名称 */
        public FieldNode<MetadataDictModel, String> metadataName =  createFieldNode("metadataName","metadata_name",String.class,JdbcType.VARCHAR);
    	/** 元数据别名 */
        public FieldNode<MetadataDictModel, String> metadataAlias =  createFieldNode("metadataAlias","metadata_alias",String.class,JdbcType.VARCHAR);
    	/** 元数据描述 */
        public FieldNode<MetadataDictModel, String> metadataMemo =  createFieldNode("metadataMemo","metadata_memo",String.class,JdbcType.VARCHAR);
    	/** 关联元数据ID */
        public FieldNode<MetadataDictModel, Integer> metadataParentid =  createFieldNode("metadataParentid","metadata_parentid",Integer.class,JdbcType.INTEGER);
    	/** 元数据类型 */
        public FieldNode<MetadataDictModel, Integer> metadataType =  createFieldNode("metadataType","metadata_type",Integer.class,JdbcType.INTEGER);
    	/** 表记录是否缓存 */
        public FieldNode<MetadataDictModel, Boolean> metadataCached =  createFieldNode("metadataCached","metadata_cached",Boolean.class,JdbcType.BIT);
    	/** 归属数据库 */
        public FieldNode<MetadataDictModel, String> metadataDb =  createFieldNode("metadataDb","metadata_db",String.class,JdbcType.VARCHAR);
    	/** 是否auto创建 */
        public FieldNode<MetadataDictModel, Boolean> metadataAutocreate =  createFieldNode("metadataAutocreate","metadata_autocreate",Boolean.class,JdbcType.BIT);
    	/** 脚本 */
        public FieldNode<MetadataDictModel, String> metadataStmt =  createFieldNode("metadataStmt","metadata_stmt",String.class,JdbcType.VARCHAR);
    	/** 唯一索引字段 */
        public FieldNode<MetadataDictModel, String> metadataIndex =  createFieldNode("metadataIndex","metadata_index",String.class,JdbcType.VARCHAR);
    	/** 说明 */
        public FieldNode<MetadataDictModel, String> metadataRemark =  createFieldNode("metadataRemark","metadata_remark",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataDictModel, String> metadataSortFields =  createFieldNode("metadataSortFields","metadata_sort_fields",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataDictModel, Byte> refSrc =  createFieldNode("refSrc","ref_src",Byte.class,JdbcType.TINYINT);
    	/**  */
        public FieldNode<MetadataDictModel, String> refObject =  createFieldNode("refObject","ref_object",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataDictModel, String> refParam =  createFieldNode("refParam","ref_param",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataDictModel, Boolean> metadataReadonly =  createFieldNode("metadataReadonly","metadata_readonly",Boolean.class,JdbcType.BIT);
    	/**  */
        public FieldNode<MetadataDictModel, Boolean> metadataAddDel =  createFieldNode("metadataAddDel","metadata_add_del",Boolean.class,JdbcType.BIT);
    	/**  */
        public FieldNode<MetadataDictModel, Boolean> expTagtableHead =  createFieldNode("expTagtableHead","exp_tagtable_head",Boolean.class,JdbcType.BIT);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(MetadataDictModel.class,"metadata_dict",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<MetadataDictModel> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<MetadataDictModel>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}