package com.kunlong.sys.service;

import com.kunlong.sys.domain.SysDomain;

public interface SysEventService {
	
	/**
	 * 告警
	 * @param domain
	 * @param level
	 * @param info
	 */
	void alert(SysDomain domain, Integer level, String subject, String info);
}
