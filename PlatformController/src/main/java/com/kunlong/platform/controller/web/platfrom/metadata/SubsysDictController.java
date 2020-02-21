package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.service.SubsysDictService;
import com.kunlong.platform.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 *
 * 
 * @author ljm
 *
 */
@Api(value = "子系统", description = "子系统")

@RequestMapping(ApiConstants.PREFIX_SYS+"/subsysdict")
@RestController
public class SubsysDictController extends BaseController {

	@Autowired
	private SubsysDictService subsysDictService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	public @ResponseBody
	@ApiOperation(value = "query", notes = "query", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
	PageResult<SubsysDict> query() {
		SubsysDict.QueryParam queryParam = new SubsysDict.QueryParam();
		queryParam.setLimit(-1);
		List<SubsysDict> list = subsysDictService.findByQueryParam(queryParam);
		PageResult<SubsysDict> pageResult = new PageResult<SubsysDict>();
		pageResult.setData(list);
		pageResult.setTotal(subsysDictService.countByQueryParam(queryParam));
		return pageResult;
	}

	@RequestMapping("/findById/{id}")
	public JsonResult<SubsysDict> findById(@PathVariable("id") Integer id) throws IOException {
		return JsonResult.success(subsysDictService.findById(id));
	}

	@RequestMapping("/save")
	public JsonResult<Integer> save(@RequestBody SubsysDict subsysDict) {

		if (subsysDict.getSubsysId() == null) {
			subsysDictService.save(subsysDict);
		} else {
			subsysDictService.update(subsysDict);
		}

		return JsonResult.success(subsysDict.getSubsysId());
	}



	@PostMapping("/deleteById/{id}")
	public JsonResult<Integer> deleteById(@PathVariable("id") Integer id) throws IOException {
		subsysDictService.deleteById(id) ;

		return JsonResult.success();
	}

}
