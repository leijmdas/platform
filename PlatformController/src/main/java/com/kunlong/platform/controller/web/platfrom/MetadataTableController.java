package com.kunlong.platform.controller.web.platfrom;

import app.support.query.PageResult;
import com.kunlong.dubbo.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.dubbo.api.model.SelectSqlDTO;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.utils.SqlSessionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
	SqlSessionUtil sqlSessionUtil;

	@Autowired
    MetadataDictModelService metadataDictModelService;


    @PostMapping("/selectTable")
    public @ResponseBody
    PageResult<Map<String, Object>> selectTable(@RequestBody MetadataQueryDTO metadataQuery) {
        if (metadataQuery.getParam() == null) {
            metadataQuery.setParam(new SelectSqlDTO());

        }

        if (metadataQuery.getParam().getMetadataId() != null) {
            MetadataDictModel metadataDict = metadataDictModelService.findById(metadataQuery.getParam().getMetadataId());
            if (metadataDict == null) {
                throw new RuntimeException("表不存在！");
            }
            metadataQuery.getParam().setDb(metadataDict.getMetadataDb());
            metadataQuery.getParam().setTable(metadataDict.getMetadataName());
        }

        List<Map<String, Object>> rs = sqlSessionUtil.selectTable(metadataQuery);
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setData(rs);
        long count = sqlSessionUtil.countTable(metadataQuery);
        pageResult.setTotal(count);
        return pageResult;
    }


}
