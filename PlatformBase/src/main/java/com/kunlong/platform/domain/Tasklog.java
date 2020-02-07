package com.kunlong.platform.domain;

import java.io.Serializable;
import javax.persistence.Id;
import javax.validation.constraints.*;

import com.kunlong.platform.model.KunlongModel;
import org.mybatis.hbatis.core.type.JdbcType;
import org.mybatis.hbatis.core.annotation.*;
import org.mybatis.hbatis.core.*;
import java.lang.Integer;
import java.lang.String;
import java.lang.Byte;
import java.util.Date;
import org.mybatis.hbatis.orm.criteria.support.query.AbstractQueryParam;
import org.mybatis.hbatis.orm.criteria.support.query.SortOrders;

/**
 * Tasklog 
 * @author generator
 * @date 2020年01月30日
 */
@Table(Tasklog.EntityNode.class)
public class Tasklog extends KunlongModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	  * 用户日志标识
	  * nullable:true,length:11
	  */

	@Id
	@Column(primaryKey = true,autoIncrement = true,comment = "用户日志标识")
	private Integer id;
	/**
	  * 用户标识
	  * nullable:false,length:32
	  */
	@Column(comment = "用户标识")	
	@NotNull
	private String user;
	/**
	  * 用户ip
	  * nullable:true,length:32
	  */
	@Column(comment = "用户ip")	
	private String ip;
	/**
	  * 操作类型
	  * nullable:false,length:4
	  */
	@Column(comment = "操作类型")	
	@NotNull
	private Byte oprtType;
	/**
	  * 操作名称
	  * nullable:false,length:64
	  */
	@Column(comment = "操作名称")	
	@NotNull
	private String oprt;
	/**
	  * 
	  * nullable:true,length:512
	  */
	@Column(comment = "")	
	private String params;
	/**
	  * 返回码
	  * nullable:false,length:11
	  */
	@Column(comment = "返回码")	
	@NotNull
	private Integer code;
	/**
	  * 操作时间
	  * nullable:true,length:19
	  */
	@Column(comment = "操作时间")	
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

    public static class EntityNode extends AbstractEntityNode<Tasklog> {
        public static final EntityNode INSTANCE = new EntityNode("t");;
    	/** 用户日志标识 */
        public FieldNode<Tasklog, Integer> id =  createFieldNode("id","id",Integer.class,JdbcType.INTEGER);
    	/** 用户标识 */
        public FieldNode<Tasklog, String> user =  createFieldNode("user","user",String.class,JdbcType.VARCHAR);
    	/** 用户ip */
        public FieldNode<Tasklog, String> ip =  createFieldNode("ip","ip",String.class,JdbcType.VARCHAR);
    	/** 操作类型 */
        public FieldNode<Tasklog, Byte> oprtType =  createFieldNode("oprtType","oprt_type",Byte.class,JdbcType.TINYINT);
    	/** 操作名称 */
        public FieldNode<Tasklog, String> oprt =  createFieldNode("oprt","oprt",String.class,JdbcType.VARCHAR);
    	/**  */
        public FieldNode<Tasklog, String> params =  createFieldNode("params","params",String.class,JdbcType.VARCHAR);
    	/** 返回码 */
        public FieldNode<Tasklog, Integer> code =  createFieldNode("code","code",Integer.class,JdbcType.INTEGER);
    	/** 操作时间 */
        public FieldNode<Tasklog, Date> oprtTime =  createFieldNode("oprtTime","oprt_time",Date.class,JdbcType.TIMESTAMP);
	
        /**
         * @param alias 别名
         */
        public EntityNode(String alias) {
            super(Tasklog.class,"tasklog",alias);
        }
    }
    
    //-- 实体参数(允许新增属性) 
	public static class QueryParam extends AbstractQueryParam<Tasklog> {
		public QueryParam() {
			this.setSortOrders(new SortOrders<Tasklog>(EntityNode.INSTANCE));
		}
	}
	
	public static enum ValueField {
	}
    // ==== 自定义属性 ====
}