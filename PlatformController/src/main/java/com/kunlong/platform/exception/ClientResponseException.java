package com.kunlong.platform.exception;

import com.xw.ipark.api.exception.ApiBusinessException;

public class ClientResponseException extends ApiBusinessException {

	public ClientResponseException(String msg) {
		super(msg);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int httpStatus;
	private String code;

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
