package com.kunlong.sys.queryParam;

import com.kunlong.sys.domain.SysPosition;
import com.kunlong.core.support.query.QueryParam;

public class SysPositionQueryParam extends QueryParam<SysPosition> {
	/**
	 * 级联子
	 */
	private Boolean cascadeOrgChild;

	public Boolean getCascadeOrgChild() {
		return cascadeOrgChild;
	}

	public void setCascadeOrgChild(Boolean cascadeOrgChild) {
		this.cascadeOrgChild = cascadeOrgChild;
	}

}
