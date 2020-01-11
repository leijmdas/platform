package com.kunlong.api.model;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.lang.Boolean;
import java.lang.Byte;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * MetadataDictModel
 * @author generator
 * @date 2019年12月29日
 */
@ApiModel(value="MetadataDictModelDTO",description="元数据主表")
public class MetadataDictModelDTO extends KunlongApiModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 元数据标识号
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "元数据标识号",required = false,notes = "元数据标识号 [自增]")
	private Integer metadataId;
	/**
	  * 子系统标识号
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "子系统标识号",required = true,notes = "子系统标识号 ")
	private Integer subsysId;
	/**
	  * 排序方式
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "排序方式",required = true,notes = "排序方式 ")
	private Integer metadataOrder;
	/**
	  * 元数据名称
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "元数据名称",required = true,notes = "元数据名称 ")
	private String metadataName;
	/**
	  * 元数据别名
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "元数据别名",required = true,notes = "元数据别名 ")
	private String metadataAlias;
	/**
	  * 元数据描述
	  * nullable:true,length:128
	  */
	@ApiModelProperty(value = "元数据描述",required = false,notes = "元数据描述 ")
	private String metadataMemo;
	/**
	  * 关联元数据ID
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "关联元数据ID",required = true,notes = "关联元数据ID ")
	private Integer metadataParentid;
	/**
	  * 元数据类型
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "元数据类型",required = true,notes = "元数据类型 ")
	private Integer metadataType;
	/**
	  * 表记录是否缓存
	  * nullable:true,length:1
	  */
	@ApiModelProperty(value = "表记录是否缓存",required = false,notes = "表记录是否缓存 ")
	private Boolean metadataCached;
	/**
	  * 归属数据库
	  * nullable:false,length:32
	  */
	@ApiModelProperty(value = "归属数据库",required = true,notes = "归属数据库 ")
	private String metadataDb;
	/**
	  * 是否auto创建
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "是否auto创建",required = true,notes = "是否auto创建 ")
	private Boolean metadataAutocreate;
	/**
	  * 脚本
	  * nullable:true,length:1,024
	  */
	@ApiModelProperty(value = "脚本",required = false,notes = "脚本 ")
	private String metadataStmt;
	/**
	  * 唯一索引字段
	  * nullable:true,length:256
	  */
	@ApiModelProperty(value = "唯一索引字段",required = false,notes = "唯一索引字段 ")
	private String metadataIndex;
	/**
	  * 说明
	  * nullable:true,length:1,024
	  */
	@ApiModelProperty(value = "说明",required = false,notes = "说明 ")
	private String metadataRemark;
	/**
	  * 
	  * nullable:false,length:256
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String metadataSortFields;
	/**
	  * 
	  * nullable:false,length:4
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private Byte refSrc;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refObject;
	/**
	  * 
	  * nullable:false,length:256
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String refParam;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private Boolean metadataReadonly;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private Boolean metadataAddDel;
	/**
	  * 
	  * nullable:false,length:1
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
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
    // ==== 自定义属性 ====


}