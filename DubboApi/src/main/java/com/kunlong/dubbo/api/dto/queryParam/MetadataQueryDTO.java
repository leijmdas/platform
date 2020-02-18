package com.kunlong.dubbo.api.dto.queryParam;

import app.support.query.PageQueryParam;
import com.kunlong.dubbo.api.model.SelectSqlDTO;

public class MetadataQueryDTO extends PageQueryParam<SelectSqlDTO> implements java.io.Serializable {
	Integer subsysId;
	String ids;

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Integer getSubsysId() {
		return subsysId;
	}

	public void setSubsysId(Integer subsysId) {
		this.subsysId = subsysId;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	

	

}
