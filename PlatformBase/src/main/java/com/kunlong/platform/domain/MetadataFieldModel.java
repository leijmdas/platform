package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.kunlong.platform.model.KunlongModel;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.Short;
import java.lang.String;
import java.lang.Boolean;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;
/**
 * MetadataFieldModel 
 * @author generator
 * @date 2019年12月29日
 */
@Table(MetadataFieldModel.EntityNode.class)
public class MetadataFieldModel extends KunlongModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 字段标识号
	  * nullable:true,length:11
	  */
	@Column(primaryKey = true,autoIncrement = true,comment = "字段标识号")	
	private Integer fieldId;
	/**
	  * 元数据主表ID
	  * nullable:false,length:11
	  */
	@Column(comment = "元数据主表ID")	
	@NotNull
	private Integer metadataId;
	/**
	  * 显示顺序
	  * nullable:false,length:6
	  */
	@Column(comment = "显示顺序")	
	@NotNull
	private Short fieldOrder;
	/**
	  * 字段名称
	  * nullable:false,length:64
	  */
	@Column(comment = "字段名称")	
	@NotNull
	private String fieldName;
	/**
	  * 字段描述
	  * nullable:true,length:128
	  */
	@Column(comment = "字段描述")	
	private String fieldMemo;
	/**
	  * 字段类型
	  * nullable:false,length:11
	  */
	@Column(comment = "字段类型")	
	@NotNull
	private String fieldType;
	/**
	  * 是否主键
	  * nullable:false,length:1
	  */
	@Column(comment = "是否主键")	
	@NotNull
	private Boolean fieldPk;
	/**
	  * 自动生成
	  * nullable:false,length:1
	  */
	@Column(comment = "自动生成")	
	@NotNull
	private Boolean fieldAuto;
	/**
	  * 是否显示
	  * nullable:false,length:1
	  */
	@Column(comment = "是否显示")	
	@NotNull
	private Boolean fieldVisible;
	/**
	  * 字段大小长度
	  * nullable:false,length:11
	  */
	@Column(comment = "字段大小长度")	
	@NotNull
	private Integer fieldSize;
	/**
	  * 显示大小
	  * nullable:false,length:11
	  */
	@Column(comment = "显示大小")	
	@NotNull
	private Integer fieldDisplaysize;
	/**
	  * 是否只读
	  * nullable:false,length:1
	  */
	@Column(comment = "是否只读")	
	@NotNull
	private Boolean fieldReadonly;
	/**
	  * 默认值
	  * nullable:false,length:64
	  */
	@Column(comment = "默认值")	
	@NotNull
	private String fieldDefault;
	/**
	  * 数据来源
	  * nullable:false,length:11
	  */
	@Column(comment = "数据来源")	
	@NotNull
	private Integer fieldSrc;
	/**
	  * 
	  * nullable:false,length:255
	  */
	@Column(comment = "")	
	@NotNull
	private String refObject;
	/**
	  * 
	  * nullable:false,length:1,000
	  */
	@Column(comment = "")	
	@NotNull
	private String refParameter;
	/**
	  * 关联外表字段
	  * nullable:true,length:64
	  */
	@Column(comment = "关联外表字段")	
	private String refField;
	/**
	  * 关联外表显示字段
	  * nullable:true,length:64
	  */
	@Column(comment = "关联外表显示字段")	
	private String refDisplayID;
	/**
	  * 关联外表条件
	  * nullable:true,length:255
	  */
	@Column(comment = "关联外表条件")	
	private String refFilter;
	/**
	  * 是否计算字段
	  * nullable:false,length:1
	  */
	@Column(comment = "是否计算字段")	
	@NotNull
	private Boolean fieldIscal;
	/**
	  * 必须填
	  * nullable:false,length:255
	  */
	@Column(comment = "必须填")	
	@NotNull
	private String fieldRemark;
	/**
	  * 是否为null
	  * nullable:false,length:1
	  */
	@Column(comment = "是否为null")	
	@NotNull
	private Boolean fieldIsNull;
	/**
	  * 
	  * nullable:false,length:32
	  */
	@Column(comment = "")	
	@NotNull
	private String refTable;
	/**
	  * 
	  * nullable:false,length:8
	  */
	@Column(comment = "")	
	@NotNull
	private String fieldFormat;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@Column(comment = "")	
	private Integer fieldComponent;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@Column(comment = "")	
	@NotNull
	private String refPool;
	/**
	  * 
	  * nullable:false,length:64
	  */
	@Column(comment = "")	
	@NotNull
	private String fieldMin;
	/**
	  * 
	  * nullable:false,length:64
	  */
	@Column(comment = "")	
	@NotNull
	private String fieldMax;
    public Integer getFieldId(){
    	return this.fieldId;
    }
    public void setFieldId(Integer fieldId){
    	this.fieldId = fieldId;
    }
    public Integer getMetadataId(){
    	return this.metadataId;
    }
    public void setMetadataId(Integer metadataId){
    	this.metadataId = metadataId;
    }
    public Short getFieldOrder(){
    	return this.fieldOrder;
    }
    public void setFieldOrder(Short fieldOrder){
    	this.fieldOrder = fieldOrder;
    }
    public String getFieldName(){
    	return this.fieldName;
    }
    public void setFieldName(String fieldName){
    	this.fieldName = fieldName;
    }
    public String getFieldMemo(){
    	return this.fieldMemo;
    }
    public void setFieldMemo(String fieldMemo){
    	this.fieldMemo = fieldMemo;
    }
    public String getFieldType(){
    	return this.fieldType;
    }
    public void setFieldType(String fieldType){
    	this.fieldType = fieldType;
    }
    public Boolean getFieldPk(){
    	return this.fieldPk;
    }
    public void setFieldPk(Boolean fieldPk){
    	this.fieldPk = fieldPk;
    }
    public Boolean getFieldAuto(){
    	return this.fieldAuto;
    }
    public void setFieldAuto(Boolean fieldAuto){
    	this.fieldAuto = fieldAuto;
    }
    public Boolean getFieldVisible(){
    	return this.fieldVisible;
    }
    public void setFieldVisible(Boolean fieldVisible){
    	this.fieldVisible = fieldVisible;
    }
    public Integer getFieldSize(){
    	return this.fieldSize;
    }
    public void setFieldSize(Integer fieldSize){
    	this.fieldSize = fieldSize;
    }
    public Integer getFieldDisplaysize(){
    	return this.fieldDisplaysize;
    }
    public void setFieldDisplaysize(Integer fieldDisplaysize){
    	this.fieldDisplaysize = fieldDisplaysize;
    }
    public Boolean getFieldReadonly(){
    	return this.fieldReadonly;
    }
    public void setFieldReadonly(Boolean fieldReadonly){
    	this.fieldReadonly = fieldReadonly;
    }
    public String getFieldDefault(){
    	return this.fieldDefault;
    }
    public void setFieldDefault(String fieldDefault){
    	this.fieldDefault = fieldDefault;
    }
    public Integer getFieldSrc(){
    	return this.fieldSrc;
    }
    public void setFieldSrc(Integer fieldSrc){
    	this.fieldSrc = fieldSrc;
    }
    public String getRefObject(){
    	return this.refObject;
    }
    public void setRefObject(String refObject){
    	this.refObject = refObject;
    }
    public String getRefParameter(){
    	return this.refParameter;
    }
    public void setRefParameter(String refParameter){
    	this.refParameter = refParameter;
    }
    public String getRefField(){
    	return this.refField;
    }
    public void setRefField(String refField){
    	this.refField = refField;
    }
    public String getRefDisplayID(){
    	return this.refDisplayID;
    }
    public void setRefDisplayID(String refDisplayID){
    	this.refDisplayID = refDisplayID;
    }
    public String getRefFilter(){
    	return this.refFilter;
    }
    public void setRefFilter(String refFilter){
    	this.refFilter = refFilter;
    }
    public Boolean getFieldIscal(){
    	return this.fieldIscal;
    }
    public void setFieldIscal(Boolean fieldIscal){
    	this.fieldIscal = fieldIscal;
    }
    public String getFieldRemark(){
    	return this.fieldRemark;
    }
    public void setFieldRemark(String fieldRemark){
    	this.fieldRemark = fieldRemark;
    }
    public Boolean getFieldIsNull(){
    	return this.fieldIsNull;
    }
    public void setFieldIsNull(Boolean fieldIsNull){
    	this.fieldIsNull = fieldIsNull;
    }
    public String getRefTable(){
    	return this.refTable;
    }
    public void setRefTable(String refTable){
    	this.refTable = refTable;
    }
    public String getFieldFormat(){
    	return this.fieldFormat;
    }
    public void setFieldFormat(String fieldFormat){
    	this.fieldFormat = fieldFormat;
    }
    public Integer getFieldComponent(){
    	return this.fieldComponent;
    }
    public void setFieldComponent(Integer fieldComponent){
    	this.fieldComponent = fieldComponent;
    }
    public String getRefPool(){
    	return this.refPool;
    }
    public void setRefPool(String refPool){
    	this.refPool = refPool;
    }
    public String getFieldMin(){
    	return this.fieldMin;
    }
    public void setFieldMin(String fieldMin){
    	this.fieldMin = fieldMin;
    }
    public String getFieldMax(){
    	return this.fieldMax;
    }
    public void setFieldMax(String fieldMax){
    	this.fieldMax = fieldMax;
    }

    public static class EntityNode extends AbstractEntityNode<MetadataFieldModel> {
        public static final EntityNode INSTANCE = new EntityNode("mf");;
    	/** 字段标识号 */
        public FieldNode<MetadataFieldModel, Integer> fieldId =  createFieldNode("fieldId","field_id",Integer.class,JdbcType.INTEGER);
    	/** 元数据主表ID */
        public FieldNode<MetadataFieldModel, Integer> metadataId =  createFieldNode("metadataId","metadata_id",Integer.class,JdbcType.INTEGER);
    	/** 显示顺序 */
        public FieldNode<MetadataFieldModel, Short> fieldOrder =  createFieldNode("fieldOrder","field_order",Short.class,JdbcType.SMALLINT);
    	/** 字段名称 */
        public FieldNode<MetadataFieldModel, String> fieldName =  createFieldNode("fieldName","field_name",String.class,JdbcType.VARCHAR);
    	/** 字段描述 */
        public FieldNode<MetadataFieldModel, String> fieldMemo =  createFieldNode("fieldMemo","field_memo",String.class,JdbcType.VARCHAR);
    	/** 字段类型 */
        public FieldNode<MetadataFieldModel, String> fieldType =  createFieldNode("fieldType","field_type",String.class,JdbcType.VARCHAR);
    	/** 是否主键 */
        public FieldNode<MetadataFieldModel, Boolean> fieldPk =  createFieldNode("fieldPk","field_pk",Boolean.class,JdbcType.BIT);
    	/** 自动生成 */
        public FieldNode<MetadataFieldModel, Boolean> fieldAuto =  createFieldNode("fieldAuto","field_auto",Boolean.class,JdbcType.BIT);
    	/** 是否显示 */
        public FieldNode<MetadataFieldModel, Boolean> fieldVisible =  createFieldNode("fieldVisible","field_visible",Boolean.class,JdbcType.BIT);
    	/** 字段大小长度 */
        public FieldNode<MetadataFieldModel, Integer> fieldSize =  createFieldNode("fieldSize","field_size",Integer.class,JdbcType.INTEGER);
    	/** 显示大小 */
        public FieldNode<MetadataFieldModel, Integer> fieldDisplaysize =  createFieldNode("fieldDisplaysize","field_displaysize",Integer.class,JdbcType.INTEGER);
    	/** 是否只读 */
        public FieldNode<MetadataFieldModel, Boolean> fieldReadonly =  createFieldNode("fieldReadonly","field_readonly",Boolean.class,JdbcType.BIT);
    	/** 默认值 */
        public FieldNode<MetadataFieldModel, String> fieldDefault =  createFieldNode("fieldDefault","field_default",String.class,JdbcType.VARCHAR);
    	/** 数据来源 */
        public FieldNode<MetadataFieldModel, Integer> fieldSrc =  createFieldNode("fieldSrc","field_src",Integer.class,JdbcType.INTEGER);
    	/**  */
        public FieldNode<MetadataFieldModel, String> refObject =  createFieldNode("refObject","ref_object",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataFieldModel, String> refParameter =  createFieldNode("refParameter","ref_parameter",String.class,JdbcType.VARCHAR);
    	/** 关联外表字段 */
        public FieldNode<MetadataFieldModel, String> refField =  createFieldNode("refField","ref_field",String.class,JdbcType.VARCHAR);
    	/** 关联外表显示字段 */
        public FieldNode<MetadataFieldModel, String> refDisplayID =  createFieldNode("refDisplayID","ref_displayID",String.class,JdbcType.VARCHAR);
    	/** 关联外表条件 */
        public FieldNode<MetadataFieldModel, String> refFilter =  createFieldNode("refFilter","ref_filter",String.class,JdbcType.VARCHAR);
    	/** 是否计算字段 */
        public FieldNode<MetadataFieldModel, Boolean> fieldIscal =  createFieldNode("fieldIscal","field_iscal",Boolean.class,JdbcType.BIT);
    	/** 必须填 */
        public FieldNode<MetadataFieldModel, String> fieldRemark =  createFieldNode("fieldRemark","field_remark",String.class,JdbcType.VARCHAR);
    	/** 是否为null */
        public FieldNode<MetadataFieldModel, Boolean> fieldIsNull =  createFieldNode("fieldIsNull","field_isNull",Boolean.class,JdbcType.BIT);
    	/**  */
        public FieldNode<MetadataFieldModel, String> refTable =  createFieldNode("refTable","ref_table",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataFieldModel, String> fieldFormat =  createFieldNode("fieldFormat","field_format",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataFieldModel, Integer> fieldComponent =  createFieldNode("fieldComponent","field_component",Integer.class,JdbcType.INTEGER);
    	/**  */
        public FieldNode<MetadataFieldModel, String> refPool =  createFieldNode("refPool","ref_pool",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataFieldModel, String> fieldMin =  createFieldNode("fieldMin","field_min",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<MetadataFieldModel, String> fieldMax =  createFieldNode("fieldMax","field_max",String.class,JdbcType.VARCHAR);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(MetadataFieldModel.class,"metadata_field",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<MetadataFieldModel> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<MetadataFieldModel>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}