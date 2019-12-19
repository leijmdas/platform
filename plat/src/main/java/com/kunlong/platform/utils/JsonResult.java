package com.kunlong.platform.utils;

import com.kunlong.platform.model.KunlongModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Json 返回结果
 * @author monter
 */
@ApiModel("Json 返回结果")
public class JsonResult<T> extends KunlongModel implements java.io.Serializable {

	static String CODE_FAILER = "10001";
	static String CODE_SUCCESS = "0";
	/**
	 * 是否成功
	 */
	@ApiModelProperty(name = "status", notes = "返回状态")
	private boolean status;
	@ApiModelProperty(name = "msg", notes = "完整信息")
	private String msg;
	@ApiModelProperty(name = "code", notes = "返回代码参数")
	private String code;
	/**
	 * 数据
	 */
	@ApiModelProperty(name = "data", notes = "返回参数")
	private T data;

	public boolean isSuccess() {
		return status;
	}


	public JsonResult<T> setSuccess(boolean success) {
		this.status = success;
		return this;
	}


	public JsonResult() {
	}


	private JsonResult(String code, boolean status) {
		this.code = code;
		this.status = status;
		if(CODE_SUCCESS.equals(code)){
			this.msg="成功";
		}
	}


	private JsonResult(String code, boolean status, T data) {
		this.code = code;
		this.status = status;
		this.data = data;
	}


	public boolean isStatus() {
		return status;
	}


	public JsonResult<T> setStatus(boolean status) {
		this.status = status;
		return this;
	}


	public String getMsg() {
		return msg;
	}


	public JsonResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}


	public String getCode() {
		return code;
	}


	public JsonResult<T> setCode(String code) {
		this.code = code;
		return this;
	}


	public T getData() {
		return data;
	}


	public JsonResult<T> setData(T data) {
		this.data = data;
		return this;
	}


	public boolean isCodeSuccess() {
		return code.equals(CODE_SUCCESS);
	}


	public static <T> JsonResult<T> success() {
		JsonResult<T> result = new JsonResult<T>( CODE_SUCCESS, true );

		return result;
	}


	public static <T> JsonResult<T> failure() {
		JsonResult<T> result = new JsonResult<T>("10001", false);
		return result;
	}


	public static <T> JsonResult<T> success(T data) {
		JsonResult<T> result = new JsonResult<T>(CODE_SUCCESS, true, data);
		result.setMsg("成功");
		return result;
	}


	public static <T> JsonResult<T> failure(T data) {
		JsonResult<T> result = new JsonResult<T>("10001", false, data);
		result.setMsg("失败");
		return result;
	}


	public static <T> JsonResult<T> failure(T data, String msg) {
		JsonResult<T> result = new JsonResult<T>("10001", false, data);
		result.setMsg(msg);
		return result;
	}




}
