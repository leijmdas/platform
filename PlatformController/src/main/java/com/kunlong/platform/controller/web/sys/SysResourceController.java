package com.kunlong.platform.controller.web.sys;

import app.support.query.PageResult;
import cn.integriti.center.api.dto.queryParam.SysResourceQueryDTO;
import cn.integriti.center.api.model.SysResourceDTO;
import cn.integriti.center.api.model.SysResourceGroupDTO;
import cn.integriti.center.api.service.SysResourceApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * SysResource
 * 
 * @author zz
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS+"/resource")
@Controller
public class SysResourceController extends BaseController {

	//@Autowired
	@Reference(lazy = true, version = "${dubbo.service.version}")
	private SysResourceApiService service;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
    PageResult<SysResourceDTO>  query(@RequestBody SysResourceQueryDTO qp) {
		
		return this.service.query(qp);
	}

	@RequestMapping(value = "queryGroupAndResources", method = RequestMethod.POST)
	public @ResponseBody
    List<SysResourceGroupDTO> queryGroupAndResources() {
		return this.service.findAllResources();
	}
}
