package com.kunlong.platform.controller.web.platfrom;


import app.support.query.PageResult;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataFieldModelService;
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
@RequestMapping(ApiConstants.PREFIX_SYS+"/metadatafield")
public final class MetadataFieldController {
    @Autowired
    MetadataFieldModelService metadataFieldModelService;
    @Autowired
    MetadataDictModelService metadataDictModelService;



    @RequestMapping("/findById/{id}")
    public JsonResult<MetadataFieldModel> findById(@PathVariable("id") Integer id, HttpServletResponse response) throws IOException {
     return   JsonResult.success(metadataFieldModelService.findById(id))    ;
    }

    @RequestMapping("/save")
    public JsonResult<Integer> save(@RequestBody MetadataFieldModel metadataDictModel) {

        if (metadataDictModel.getFieldId() == null) {
            metadataFieldModelService.save(metadataDictModel);
        } else {
            metadataFieldModelService.update(metadataDictModel);
        }

        return JsonResult.success(metadataDictModel.getMetadataId());
    }


    @RequestMapping("/query")
    public PageResult<MetadataFieldModel> query(@RequestBody MetadataFieldModel.QueryParam queryParam) throws IOException {
        PageResult<MetadataFieldModel> pageResult = new PageResult<MetadataFieldModel>();
        queryParam.setSortBys(queryParam.getOrderBys());

        pageResult.setTotal(metadataFieldModelService.countByQueryParam(queryParam));
        pageResult.setData(metadataFieldModelService.findByQueryParam(queryParam));
        for (MetadataFieldModel metadataFieldModel : pageResult.getData()) {
            MetadataDictModel metadataDictModel = metadataDictModelService.findById(metadataFieldModel.getMetadataId());
            metadataFieldModel.setMetadataDictModel(metadataDictModel);
        }
        return pageResult;
    }

    @PostMapping("/deleteById/{id}")
    public JsonResult<Integer> deleteById(@PathVariable("id") Integer id) throws IOException {
        metadataFieldModelService.deleteById(id) ;

        return JsonResult.success();
    }




}

