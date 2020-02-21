package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.DictDataModel;
import com.kunlong.platform.domain.SubsysDict;
import com.kunlong.platform.service.DictDataModelService;
import com.kunlong.platform.service.SubsysDictService;
import com.kunlong.platform.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 *
 * 
 * @author ljm
 *
 */
@Api(value = "数据字典", description = "数据字典")
@RequestMapping(ApiConstants.PREFIX_SYS + "/dictdata")
@RestController
public class DictDataController extends BaseController {

	@Autowired
	private DictDataModelService dictDataModelService;
	@Autowired
	private SubsysDictService subsysDictService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	@ApiOperation(value = "query", notes = "query", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
	public @ResponseBody
	PageResult<DictDataModel> query(@RequestBody DictDataModel.QueryParam queryParam) throws IOException {
		queryParam.setSortBys(queryParam.getOrderBys());
		queryParam.setSortBys(queryParam.getOrderBys());
		if (queryParam.getLimit() == null) {
			queryParam.setLimit(30);
		}

		List<DictDataModel> dataModels = dictDataModelService.findByQueryParam(queryParam);
		PageResult<DictDataModel> pageResult = new PageResult<DictDataModel>();
		pageResult.setData(dataModels);
		pageResult.setTotal(dictDataModelService.countByQueryParam(queryParam));
		for(DictDataModel dataModel:dataModels){
			SubsysDict subsysDict=subsysDictService.findById(dataModel.getSubsysId());
			dataModel.setSubsysDict(subsysDict);
		}
		return pageResult;
	}

	@RequestMapping("/findById/{id}")
	public JsonResult<DictDataModel> findById(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
		return JsonResult.success(dictDataModelService.findById(id));
	}

	@RequestMapping("/save")
	public JsonResult<Integer> save(@RequestBody DictDataModel dataModel) {

		if (dataModel.getId() == null) {
			dictDataModelService.save(dataModel);
		} else {
			dictDataModelService.update(dataModel);
		}

		return JsonResult.success(dataModel.getId());
	}



	@PostMapping("/deleteById/{id}")
	public JsonResult<Integer> deleteById(@PathVariable("id") Integer id) throws IOException {
		dictDataModelService.deleteById(id) ;

		return JsonResult.success();
	}
}
