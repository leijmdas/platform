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

	static Integer CODE_FAIL  = 10001;
	static Integer CODE_SUCCESS = 0;

    @ApiModelProperty(name = "code", notes = "返回代码参数")
    private Integer code;
	@ApiModelProperty(name = "msg", notes = "完整信息")
	private String msg;

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	private String sCode;

    T data;

	public Integer getSubCode() {
		return subCode;
	}

	public void setSubCode(Integer subCode) {
		this.subCode = subCode;
	}

	@ApiModelProperty(name = "status", notes = "返回状态")
    private Integer subCode;


	public JsonResult() {
	}


	private JsonResult(Integer code    ) {
		this.code = code;
		if(CODE_SUCCESS.equals(code)){
			this.msg="成功";
		}
    }


    private JsonResult(Integer code, T data) {
        this.code = code;
        this.data = data;
    }


	public String getMsg() {
		return msg;
	}


	public JsonResult<T> setMsg(String msg) {
		this.msg = msg;
		return this;
	}


	public Integer getCode() {
		return code;
	}


	public JsonResult<T> setCode(Integer code) {
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


    public static <T> JsonResult<T> success() {
        return new JsonResult<T>(CODE_SUCCESS);
    }


    public static <T> JsonResult<T> failure() {
        return new JsonResult<T>(10001);

    }


	public static <T> JsonResult<T> success(T data) {
		JsonResult<T> result = new JsonResult<T>(CODE_SUCCESS, data);
		result.setMsg("成功");
		return result;
	}

	public static <T> JsonResult<T> success(T data, String msg) {
		JsonResult<T> result = new JsonResult<T>(CODE_SUCCESS, data);
		result.setMsg(msg);
		return result;
	}


    public static <T> JsonResult<T> failure(T data) {
        JsonResult<T> result = new JsonResult<T>(CODE_FAIL, data);
        result.setMsg("失败");
        return result;
    }


    public static <T> JsonResult<T> failure(T data, String msg) {
        JsonResult<T> result = new JsonResult<T>(CODE_FAIL, data);
        result.setMsg(msg);
        return result;
    }




}
