package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.DictDataModel;
import com.kunlong.platform.service.DictDataModelService;
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
@Api(value = "数据字典", description = "数据字典")
@RequestMapping(ApiConstants.PREFIX_SYS + "/dictdata")
@RestController
public class DictDataController extends BaseController {

	@Autowired
	private DictDataModelService dictDataModelService;

	@RequestMapping(value = "query", method = RequestMethod.POST)
	@ApiOperation(value = "query", notes = "query", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
	public @ResponseBody
	PageResult<DictDataModel> query(@RequestBody DictDataModel.QueryParam queryParam) throws IOException {
		queryParam.setSortBys(queryParam.getOrderBys());
		queryParam.setLimit(100);

		List<DictDataModel> dictDataModels = this.dictDataModelService.findByQueryParam(queryParam);
		PageResult<DictDataModel> pageResult = new PageResult<DictDataModel>();
		pageResult.setData(dictDataModels);
		pageResult.setTotal(dictDataModels.size());
		return pageResult;
	}

}
