package com.kunlong.sys.queryParam;

import com.kunlong.sys.domain.SysDict;
import com.kunlong.core.support.query.QueryParam;

public class SysDictQueryParam extends QueryParam<SysDict> {

	/**
	 * 关键值，支持code,name
	 */
	private String keywords;

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	
}
