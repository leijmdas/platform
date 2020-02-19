package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.CheckDictResult;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.utils.JsonResult;
import com.kunlong.platform.utils.KunlongUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 *
 * 
 * @author ljming
 *
 */
@Api(value = "元数据工具", description = "元数据工具")

@RequestMapping(ApiConstants.PREFIX_SYS + "/metadata/table")
@Controller
public class MetadataTableController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MetadataJoinService metadataJoinService;

    @PostMapping("/selectTable")
    public @ResponseBody
    JsonResult<PageResult<Map<String, Object>>> selectTable(@RequestBody MetadataQueryDTO queryDTO) {
        if(!metadataJoinService.checkTableExists(queryDTO.getParam().getMetadataId()))
        {
            return JsonResult.failure(null,"表不存在！");
        }
        PageResult<Map<String, Object>> pageResult = metadataJoinService.selectTable(queryDTO);
        return JsonResult.success(pageResult);

    }

    @PostMapping("/checkDict")
    public @ResponseBody
    PageResult<CheckDictResult> checkDict(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.checkDict(queryDTO.getParam().getMetadataId());
    }

    @PostMapping("/copyMaster")
    public @ResponseBody
    JsonResult<Integer> copyMaster(@RequestBody MetadataQueryDTO queryDTO) {
        Integer id = metadataJoinService.copyMaster(queryDTO.getParam().getMetadataId());
        if (id < 0) {
            return JsonResult.failure(id, "元数据表不存在！");
        }
        return JsonResult.success(id);
    }

    @PostMapping("/dropDbTable")
    public @ResponseBody
    JsonResult<Integer> dropDbTable(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.dropDbTable(queryDTO.getParam().getMetadataId());

    }

    @PostMapping("/makeDbTable")
    public @ResponseBody
    JsonResult<Integer> makeDbTable(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.makeDbTable(queryDTO.getParam().getMetadataId());

    }

    @ApiOperation(value = "makeWebPage", notes = "生成页面代码", authorizations = {@Authorization(value = ApiConstants.AUTH_API_WEB)})
    @PostMapping("/makeWebPage")
    public @ResponseBody
    JsonResult<List<String>> makeFormCode(@RequestBody MetadataQueryDTO queryDTO) throws IOException {
        List<String> ret = metadataJoinService.makeWebPage(queryDTO.getParam().getMetadataId());
        return JsonResult.success(ret);
    }


    @PostMapping("/doSortMetadataDict")
    public @ResponseBody
    JsonResult<Integer> doSortMetadataDict(@RequestBody MetadataQueryDTO queryDTO) {
        logger.info("queryDTO: {}",KunlongUtils.toJSONStringPretty(queryDTO));
        metadataJoinService.doSortMetadataDict(queryDTO.getSubsysId(),queryDTO.getIds());
        return JsonResult.success();
    }

    @PostMapping("/doSortMetadataField")
    public @ResponseBody
    JsonResult<Integer> doSortMetadataField(@RequestBody MetadataQueryDTO queryDTO) {
        logger.info("queryDTO: {}",KunlongUtils.toJSONStringPretty(queryDTO));
        metadataJoinService.doSortMetadataField(queryDTO.getIds());

        return JsonResult.success();
    }

}
