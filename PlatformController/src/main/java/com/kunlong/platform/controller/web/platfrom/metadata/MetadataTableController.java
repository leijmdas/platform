package com.kunlong.platform.controller.web.platfrom.metadata;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * 
 * @author ljming
 *
 */
@RequestMapping(ApiConstants.PREFIX_SYS + "/metadata/table")
@Controller
public class MetadataTableController extends BaseController {
    @Autowired
    MetadataJoinService metadataJoinService;

    @PostMapping("/selectTable")
    public @ResponseBody
    PageResult<Map<String, Object>> selectTable(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.selectTable(queryDTO);

    }

    @PostMapping("/checkDict")
    public @ResponseBody
    PageResult<Map<String, Object>> checkDict(@RequestBody MetadataQueryDTO queryDTO) {
        return metadataJoinService.checkDict(queryDTO);
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

}
