package com.kunlong.platform.dto;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.lang.Byte;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * Tasklog
 * @author generator
 * @date 2020年01月30日
 */
@ApiModel(value="TasklogDTO",description="")
public class TasklogDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 用户日志标识
	  * nullable:true,length:11
	  */
	@ApiModelProperty(value = "用户日志标识",required = false,notes = "用户日志标识 [自增]")
	private Integer id;
	/**
	  * 用户标识
	  * nullable:false,length:32
	  */
	@ApiModelProperty(value = "用户标识",required = true,notes = "用户标识 ")
	private String user;
	/**
	  * 用户ip
	  * nullable:true,length:32
	  */
	@ApiModelProperty(value = "用户ip",required = false,notes = "用户ip ")
	private String ip;
	/**
	  * 操作类型
	  * nullable:false,length:4
	  */
	@ApiModelProperty(value = "操作类型",required = true,notes = "操作类型 ")
	private Byte oprtType;
	/**
	  * 操作名称
	  * nullable:false,length:64
	  */
	@ApiModelProperty(value = "操作名称",required = true,notes = "操作名称 ")
	private String oprt;
	/**
	  * 
	  * nullable:true,length:512
	  */
	@ApiModelProperty(value = "",required = false,notes = " ")
	private String params;
	/**
	  * 返回码
	  * nullable:false,length:11
	  */
	@ApiModelProperty(value = "返回码",required = true,notes = "返回码 ")
	private Integer code;
	/**
	  * 操作时间
	  * nullable:true,length:19
	  */
	@ApiModelProperty(value = "操作时间",required = false,notes = "操作时间 ")
	private Date oprtTime;
    public Integer getId(){
    	return this.id;
    }
    public void setId(Integer id){
    	this.id = id;
    }
    public String getUser(){
    	return this.user;
    }
    public void setUser(String user){
    	this.user = user;
    }
    public String getIp(){
    	return this.ip;
    }
    public void setIp(String ip){
    	this.ip = ip;
    }
    public Byte getOprtType(){
    	return this.oprtType;
    }
    public void setOprtType(Byte oprtType){
    	this.oprtType = oprtType;
    }
    public String getOprt(){
    	return this.oprt;
    }
    public void setOprt(String oprt){
    	this.oprt = oprt;
    }
    public String getParams(){
    	return this.params;
    }
    public void setParams(String params){
    	this.params = params;
    }
    public Integer getCode(){
    	return this.code;
    }
    public void setCode(Integer code){
    	this.code = code;
    }
    public Date getOprtTime(){
    	return this.oprtTime;
    }
    public void setOprtTime(Date oprtTime){
    	this.oprtTime = oprtTime;
    }
    // ==== 自定义属性 ====
}