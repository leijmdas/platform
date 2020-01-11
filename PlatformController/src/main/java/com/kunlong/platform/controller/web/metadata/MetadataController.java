package com.kunlong.platform.controller.web.metadata;

import app.support.query.PageResult;
import com.kunlong.api.dto.queryParam.MetadataDictModelQueryDTO;
import com.kunlong.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;
import com.kunlong.api.service.MetadataDictApiService;
import com.kunlong.api.service.MetadataFieldApiService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * 
 * @author ljm
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS + "/metadata")
@Controller
public class MetadataController extends BaseController {

	@Reference(lazy = true, version = "${dubbo.service.version}")
	MetadataDictApiService metadataDictApiService;

	@Reference(lazy = true, version = "${dubbo.service.version}")
	MetadataFieldApiService metadataFieldApiService;

	@PostMapping("/queryTables")
	public @ResponseBody
	PageResult<MetadataDictModelDTO> queryTables(@RequestBody MetadataDictModelQueryDTO qp) {
		if (qp.getParam() == null) {
			qp.setParam(new MetadataDictModelDTO());

		}
		List<MetadataDictModelDTO> models = metadataDictApiService.query(qp);
		PageResult<MetadataDictModelDTO> pageResult = new PageResult<MetadataDictModelDTO>();
		pageResult.setData(models);
		pageResult.setTotal(metadataDictApiService.countByQueryParam(qp));

		return pageResult;
	}


	@PostMapping("/queryFields")
	public @ResponseBody
	PageResult<MetadataFieldModelDTO> queryFields(@RequestBody MetadataFieldModelQueryDTO qp) {
		if (qp.getParam() == null) {
			qp.setParam(new MetadataFieldModelDTO());

		}
		List<MetadataFieldModelDTO> models = this.metadataFieldApiService.query(qp);
		for(MetadataFieldModelDTO model :models){
			model.setMetadataDictModel(this.metadataDictApiService.findById(model.getMetadataId()));
		}
		PageResult<MetadataFieldModelDTO> pageResult = new PageResult<MetadataFieldModelDTO>();
		pageResult.setData(models);
		pageResult.setTotal(metadataFieldApiService.countByQueryParam(qp));

		return pageResult;
	}


	@PostMapping("/queryDicts")
	public @ResponseBody
	List<MetadataDictModelDTO> queryDicts(@RequestBody MetadataDictModelQueryDTO qp) {
		if (qp.getParam() == null) {
			qp.setParam(new MetadataDictModelDTO());

		}
		return this.metadataDictApiService.query(qp);
	}

	@PostMapping("/queryFieldsByTable")
	public @ResponseBody
	List<MetadataFieldModelDTO> queryFieldsByTable(@RequestParam(value = "tableName") String tableName) {
		System.out.println("tbl:" + tableName);
		return this.metadataFieldApiService.query(tableName);
	}

}
