package com.kunlong.platform.controller.web.platfrom.metadata;


import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataJoinService;
import com.kunlong.platform.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * supplier
 * Package:
 * Author:   leijiming
 * Date: Created in 2019/8/23 16:50
 */
@RestController
@RequestMapping(ApiConstants.PREFIX_SYS+"/metadatadict")
public final class MetadataDictController {
    @Autowired
    MetadataDictModelService metadataDictModelService;

    @Autowired
    MetadataJoinService metadataJoinService;

    @RequestMapping("/findById/{id}")
    public JsonResult<MetadataDictModel> findById(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
     return   JsonResult.success(metadataDictModelService.findById(id))    ;
    }

    @RequestMapping("/save")
    public JsonResult<Integer> save(@RequestBody MetadataDictModel metadataDictModel) {

        if (metadataDictModel.getMetadataId() == null) {
            metadataDictModelService.save(metadataDictModel);
        } else {
            metadataDictModelService.update(metadataDictModel);
        }

        return JsonResult.success(metadataDictModel.getMetadataId());
    }


    @RequestMapping("/query")
    public PageResult<MetadataDictModel> query(@RequestBody MetadataDictModel.QueryParam queryParam) throws IOException {
        PageResult<MetadataDictModel> pageResult = new PageResult<MetadataDictModel>();
        queryParam.setSortBys(queryParam.getOrderBys());

        pageResult.setTotal(metadataDictModelService.countByQueryParam(queryParam));
        pageResult.setData(metadataDictModelService.findByQueryParam(queryParam));
        return pageResult;
    }

    @PostMapping("/deleteById/{id}")
    public JsonResult<Integer> deleteById(@PathVariable("id") Integer id) throws IOException {
        if(metadataJoinService.checkExistsFieldByMetadataId(id)){
            return JsonResult.failure(-1,"存在字段信息，不能删除！");
        }

        metadataDictModelService.deleteById(id);

        return JsonResult.success();
    }




}

