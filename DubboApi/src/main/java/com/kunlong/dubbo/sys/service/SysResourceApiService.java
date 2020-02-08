package com.kunlong.dubbo.sys.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysResourceQueryDTO;
import com.kunlong.dubbo.sys.model.SysResourceDTO;
import com.kunlong.dubbo.sys.model.SysResourceGroupDTO;

import java.util.List;

public interface SysResourceApiService {
	/**
	 * 查询
	 * @param qp
	 * @return
	 */
	public PageResult<SysResourceDTO> query(SysResourceQueryDTO qp);
	/**
	 * 查询资源
	 * @return
	 */
	List<SysResourceGroupDTO> findAllResources();
}
