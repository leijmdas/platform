package com.kunlong.api.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.Short;
import java.lang.String;
import java.lang.Boolean;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * MetadataFieldModel
 * @author generator
 * @date 2019年12月29日
 */
@ApiModel(value="MetadataFieldModelDTO",description="")
public class MetadataFieldModelDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 字段标识号
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "字段标识号",required = false,notes = "字段标识号 [自增]")
	private Integer fieldId;
	/**
	  * 元数据主表ID
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "元数据主表ID",required = true,notes = "元数据主表ID ")
	private Integer metadataId;
	/**
	  * 显示顺序
	  * nullable:false,length:6
	  */
	@ApiModelProperty(value = "显示顺序",required = true,notes = "显示顺序 ")
	private Short fieldOrder;
	/**
	  * 字段名称
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "字段名称",required = true,notes = "字段名称 ")
	private String fieldName;
	/**
	  * 字段描述
	  * nullable:true,length:128
	  */
	@ApiModelProperty(value = "字段描述",required = false,notes = "字段描述 ")
	private String fieldMemo;
	/**
	  * 字段类型
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "字段类型",required = true,notes = "字段类型 ")
	private String fieldType;
	/**
	  * 是否主键
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否主键",required = true,notes = "是否主键 ")
	private Boolean fieldPk;
	/**
	  * 自动生成
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "自动生成",required = true,notes = "自动生成 ")
	private Boolean fieldAuto;
	/**
	  * 是否显示
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否显示",required = true,notes = "是否显示 ")
	private Boolean fieldVisible;
	/**
	  * 字段大小长度
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "字段大小长度",required = true,notes = "字段大小长度 ")
	private Integer fieldSize;
	/**
	  * 显示大小
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "显示大小",required = true,notes = "显示大小 ")
	private Integer fieldDisplaysize;
	/**
	  * 是否只读
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否只读",required = true,notes = "是否只读 ")
	private Boolean fieldReadonly;
	/**
	  * 默认值
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "默认值",required = true,notes = "默认值 ")
	private String fieldDefault;
	/**
	  * 数据来源
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "数据来源",required = true,notes = "数据来源 ")
	private Integer fieldSrc;
	/**
	  * 
	  * nullable:false,length:255
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refObject;
	/**
	  * 
	  * nullable:false,length:1,000
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refParameter;
	/**
	  * 关联外表字段
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "关联外表字段",required = false,notes = "关联外表字段 ")
	private String refField;
	/**
	  * 关联外表显示字段
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "关联外表显示字段",required = false,notes = "关联外表显示字段 ")
	private String refDisplayID;
	/**
	  * 关联外表条件
	  * nullable:true,length:255
	  */
	@ApiModelProperty(value = "关联外表条件",required = false,notes = "关联外表条件 ")
	private String refFilter;
	/**
	  * 是否计算字段
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否计算字段",required = true,notes = "是否计算字段 ")
	private Boolean fieldIscal;
	/**
	  * 必须填
	  * nullable:false,length:255
	  */
	@ApiModelProperty(value = "必须填",required = true,notes = "必须填 ")
	private String fieldRemark;
	/**
	  * 是否为null
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否为null",required = true,notes = "是否为null ")
	private Boolean fieldIsNull;
	/**
	  * 
	  * nullable:false,length:32
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refTable;
	/**
	  * 
	  * nullable:false,length:8
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String fieldFormat;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private Integer fieldComponent;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refPool;
	/**
	  * 
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String fieldMin;
	/**
	  * 
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
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
    // ==== 自定义属性 ====

	MetadataDictModelDTO metadataDictModel;



	public MetadataDictModelDTO getMetadataDictModel() {
		return metadataDictModel;
	}

	public void setMetadataDictModel(MetadataDictModelDTO metadataDictModel) {
		this.metadataDictModel = metadataDictModel;
	}


}