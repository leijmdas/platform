package com.kunlong.platform.controller.web.platfrom.sys;

import app.support.query.PageResult;
import com.kunlong.dubbo.sys.dto.queryParam.SysResourceQueryDTO;
import com.kunlong.dubbo.sys.model.SysResourceDTO;
import com.kunlong.dubbo.sys.model.SysResourceGroupDTO;
import com.kunlong.dubbo.sys.service.SysResourceApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.alibaba.dubbo.config.annotation.Reference;
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
