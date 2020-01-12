package com.kunlong.platform.controller.web.metadata;

import app.support.query.PageResult;
import com.kunlong.api.dto.queryParam.MetadataQueryDTO;
import com.kunlong.api.model.SelectSqlDTO;
import com.kunlong.metadata.model.MetadataDict;
import com.kunlong.metadata.service.MetadataDictService;
import com.kunlong.platform.consts.ApiConstants;
import com.kunlong.platform.controller.web.BaseController;
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
@RequestMapping(ApiConstants.PREFIX_SYS + "/metadata/public")
@Controller
public class MetadataQueryController extends BaseController {
	@Autowired
	SqlSessionUtil sqlSessionUtil;

	@Autowired
	MetadataDictService metadataDictService;


    @PostMapping("/selectTable")
    public @ResponseBody
    PageResult<Map<String, Object>> selectTable(@RequestBody MetadataQueryDTO qp) {
        if (qp.getParam() == null) {
            qp.setParam(new SelectSqlDTO());

        }

        if (qp.getParam().getMetadataId() != null) {
            MetadataDict metadataDict = metadataDictService.selectByPrimaryKey(qp.getParam().getMetadataId());
            if (metadataDict == null) {

                throw new RuntimeException("表不存在！");
            }
            qp.getParam().setDb(metadataDict.getMetadataDb());
            qp.getParam().setTable(metadataDict.getMetadataName());
        }

        List<Map<String, Object>> rs = sqlSessionUtil.selectTable(qp);
        PageResult<Map<String, Object>> pageResult = new PageResult<Map<String, Object>>();
        pageResult.setData(rs);
        long count = sqlSessionUtil.countTable(qp);
        pageResult.setTotal(count);
        return pageResult;
    }


}
