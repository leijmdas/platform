package com.kunlong.platform.controller.web.metadata;

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

	@PostMapping("/queryDicts")
	public @ResponseBody
	List<MetadataDictModelDTO> queryDicts(@RequestBody MetadataDictModelQueryDTO qp) {
		if (qp.getParam() == null) {
			qp.setParam(new MetadataDictModelDTO());

		}
		return this.metadataDictApiService.query(qp);
	}
	@PostMapping("/queryFields")
	public @ResponseBody
	List<MetadataFieldModelDTO> queryFields(@RequestBody MetadataFieldModelQueryDTO qp) {
		if (qp.getParam() == null) {
			qp.setParam(new MetadataFieldModelDTO());

		}
		return this.metadataFieldApiService.query(qp);
	}


}