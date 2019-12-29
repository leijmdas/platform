package com.kunlong.platform.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * DictConfig
 * @author generator
 * @date 2019年12月29日
 */
@ApiModel(value="DictConfigDTO",description="")
public class DictConfigDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "",required = false,notes = " [自增]")
	private Integer configId;
	/**
	  * 
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private Integer configType;
	/**
	  * 
	  * nullable:false,length:128
	  */
	@ApiModelProperty(value = "",required = true,notes = " ")
	private String configItem;
	/**
	  * 
	  * nullable:true,length:256
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private String configRemark;
	/**
	  * 
	  * nullable:true,length:256
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
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
    // ==== 自定义属性 ====
}