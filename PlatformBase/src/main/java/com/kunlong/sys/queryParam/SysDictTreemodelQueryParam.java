package com.kunlong.sys.queryParam;

import com.kunlong.sys.domain.SysDictTreemodel;
import com.kunlong.core.support.query.QueryParam;

public class SysDictTreemodelQueryParam extends QueryParam<SysDictTreemodel> {

	private Boolean cascadeChild;
	private String code;
	
	public Boolean getCascadeChild() {
		return cascadeChild;
	}

	public void setCascadeChild(Boolean cascadeChild) {
		this.cascadeChild = cascadeChild;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	
}
