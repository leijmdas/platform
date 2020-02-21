package com.kunlong.platform.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * DictDataModel
 * @author generator
 * @date 2020年02月20日
 */
@ApiModel(value="DictDataModelDTO",description="数据字典")
public class DictDataModelDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 字典ID
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "字典ID",required = false,notes = "字典ID [自增]")
	private Integer id;
	/**
	  * 分类编号
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "分类编号",required = false,notes = "分类编号 ")
	private Integer typeCode;
	/**
	  * 分类名称
	  * nullable:true,length:32
	  */
	@ApiModelProperty(value = "分类名称",required = false,notes = "分类名称 ")
	private String typeName;
	/**
	  * 字典编号
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "字典编号",required = false,notes = "字典编号 ")
	private Integer code;
	/**
	  * 字典名称
	  * nullable:true,length:32
	  */
	@ApiModelProperty(value = "字典名称",required = false,notes = "字典名称 ")
	private String name;
	/**
	  * 创建时间
	  * nullable:false,length:19
	  */
	@ApiModelProperty(value = "创建时间",required = true,notes = "创建时间 ")
	private Date createTime;
	/**
	  * 创建人
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "创建人",required = false,notes = "创建人 ")
	private Integer createBy;
	/**
	  * 备注
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "备注",required = false,notes = "备注 ")
	private String remark;
	/**
	  * 
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
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
    // ==== 自定义属性 ====
}