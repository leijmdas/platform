package com.kunlong.platform.controller.web;

import cn.integriti.center.api.model.SysUserDTO;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.util.SessionHolder;
import org.springframework.util.Assert;

import java.util.Map;

public class BaseController {

	public Map<Object,Object> getSessionValues(){
		return SessionHolder.getCurrentSessionValues();
	}
	
	private SysUserDTO getCurrentSysUser() {
		Map<Object,Object> vals = this.getSessionValues();
		Assert.notNull(vals,"Session不存在或已效");
		SysUserDTO su = (SysUserDTO)vals.get(SessionKeyEnum.WEB_USER.getKey());
		Assert.notNull(su,"User Session不存在或已失效");
		return su;
	}

	public Integer getCurrentUserId() {
		return getCurrentSysUser().getId();
		
	}
	
	public Integer getCurrentCorpId() {
		return getCurrentSysUser().getCorpId();
	}
	
	
}
