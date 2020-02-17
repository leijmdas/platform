package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.service.SubsysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 
 * @author ljm
 *
 */
@Api(value = "子系统", description = "子系统")

@RequestMapping(ApiConstants.PREFIX_SYS+"/subsysdict")
@Controller
public class SubsysDictController extends BaseController {

	@Autowired
	private SubsysDictService subsysDictService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	@ApiOperation(value = "query", notes = "query", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
	PageResult<SubsysDict> query() {
		SubsysDict.QueryParam queryParam=new SubsysDict.QueryParam();
		queryParam.setLimit(-1);
		List<SubsysDict> list = this.subsysDictService.findByQueryParam( queryParam );
		PageResult<SubsysDict> pageResult = new PageResult<SubsysDict>();
		pageResult.setData(list);
		pageResult.setTotal(list.size());
		return pageResult;
	}

}
