package com.kunlong.platform.controller.web;

import com.kunlong.dubbo.sys.model.SysUserDTO;
import com.kunlong.platform.consts.SessionKeyEnum;
import com.kunlong.platform.util.SessionHolder;
import com.kunlong.sys.domain.SysDictItem;
import com.kunlong.sys.service.SysDevService;
import com.kunlong.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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
