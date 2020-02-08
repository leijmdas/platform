package com.kunlong.dubbo.sys.service;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysShortlinkQueryDTO;
import com.kunlong.dubbo.sys.model.SysShortlinkDTO;

public interface SysShortlinkApiService {

	public PageResult<SysShortlinkDTO> query(SysShortlinkQueryDTO qp);
	
	public Integer save(SysShortlinkDTO entity);
	
	public SysShortlinkDTO findById(Integer userId);
	
	public void delete(Integer id);
	
	public SysShortlinkDTO findByCode(String code);

}
