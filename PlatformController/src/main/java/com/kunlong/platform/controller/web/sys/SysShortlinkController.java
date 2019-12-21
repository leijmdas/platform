package com.kunlong.platform.controller.web.sys;

import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysShortlinkQueryDTO;
import cn.integriti.center.api.model.SysShortlinkDTO;
import cn.integriti.center.api.service.SysShortlinkApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 短链
 * 
 * @author zz
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/shortlink")
@Controller
public class SysShortlinkController extends BaseController {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysShortlinkApiService sysUserService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysShortlinkDTO> query(@RequestBody SysShortlinkQueryDTO qp) {
		if(qp.getParam() == null) {
			qp.setParam(new SysShortlinkDTO());;
		}
		return this.sysUserService.query(qp);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
    Integer save(@RequestBody SysShortlinkDTO entity) {
		return this.sysUserService.save(entity);
	}
	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody
    SysShortlinkDTO getUser(@PathVariable("id") Integer id) {
		SysShortlinkDTO tmp = this.sysUserService.findById(id);
		return tmp;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean delete(@PathVariable("id") Integer id) {
		this.sysUserService.delete(id);
		return true;
	}
}
