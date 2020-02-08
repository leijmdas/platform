package com.kunlong.dubbo.sys.dto.queryParam;

import app.support.query.PageQueryParam;
import com.kunlong.dubbo.sys.model.SysDictDTO;

import java.io.Serializable;

public class SysDictQueryDTO extends PageQueryParam<SysDictDTO> implements Serializable{

	private static final long serialVersionUID = 1L;

	private String keywords;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
}
