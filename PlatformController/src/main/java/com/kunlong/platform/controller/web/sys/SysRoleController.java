package com.kunlong.platform.controller.web.sys;

import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysRoleQueryDTO;
import cn.integriti.center.api.model.SysRoleDTO;
import cn.integriti.center.api.service.SysRoleApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 企业
 * 
 * @author zz
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/role")
@Controller
public class SysRoleController extends BaseController {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysRoleApiService service;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysRoleDTO> query(@RequestBody SysRoleQueryDTO qp) {
		if(qp.getParam() == null) {
			qp.setParam(new SysRoleDTO());
		}
		qp.getParam().setCorpId(this.getCurrentCorpId());
		return this.service.query(qp);
	}

	/**
	 * 保存
	 * 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "save", method = RequestMethod.POST)
	public @ResponseBody
    Integer save(@RequestBody SysRoleDTO entity) {
		entity.setCorpId(this.getCurrentCorpId());
		return this.service.save(entity);
	}

	
	@RequestMapping(value = "get/{id}", method = RequestMethod.GET)
	public @ResponseBody
    SysRoleDTO get(@PathVariable("id") Integer id) {
		SysRoleDTO tmp = this.service.findById(id);
		return tmp;
	}

	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean delete(@PathVariable("id") Integer id) {
		this.service.delete(id);
		return true;
	}
	@RequestMapping(value = "findResourceIds/{id}", method = RequestMethod.GET)
	public @ResponseBody
    List<Integer> findResourceIds(@PathVariable("id") Integer id) {
		return this.service.findResourceIds(id);
	}
	@RequestMapping(value = "assignRoleResources/{id}", method = RequestMethod.POST)
	public @ResponseBody
    Boolean assignRoleResources(@PathVariable("id") Integer id, @RequestBody List<Integer> resIds) {
		this.service.assignRoleResources(id, resIds);
		return true;
	}
}
