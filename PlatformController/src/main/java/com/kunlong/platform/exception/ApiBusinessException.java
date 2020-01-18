package com.kunlong.platform.exception;


public class ApiBusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String code;

	private String message;
	public ApiBusinessException(String code,String msg) {
		this.message = msg;
		this.code = code;
	}
	
	public ApiBusinessException(String msg) {
		this.message = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public void setMessage(String msg) {
		this.message = msg;
	}

	public String getMessage() {
		return message;
	}
	
}
