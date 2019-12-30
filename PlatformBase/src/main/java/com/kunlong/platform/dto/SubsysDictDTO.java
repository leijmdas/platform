package com.kunlong.platform.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.lang.Short;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * SubsysDict
 * @author generator
 * @date 2019年12月29日
 */
@ApiModel(value="SubsysDictDTO",description="子系统表")
public class SubsysDictDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 子系统标识号
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "子系统标识号",required = false,notes = "子系统标识号 [自增]")
	private Integer subsysId;
	/**
	  * 
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private String subsysNo;
	/**
	  * 子系统名称
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "子系统名称",required = true,notes = "子系统名称 ")
	private String subsysName;
	/**
	  * 子系统
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "子系统",required = false,notes = "子系统 ")
	private String remark;
	/**
	  * 
	  * nullable:true,length:64
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private String ip;
	/**
	  * 
	  * nullable:true,length:2
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
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
    // ==== 自定义属性 ====
}