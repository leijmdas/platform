package com.kunlong.platform.dubbo;

import com.alibaba.fastjson.JSON;
import com.kunlong.api.dto.queryParam.MetadataDictModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.service.AuthApiService;
import com.kunlong.api.service.MetadataDictApiService;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.utils.KunlongUtils;
import netscape.javascript.JSObject;
import org.apache.dubbo.config.annotation.Service;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}",interfaceClass = MetadataDictApiService.class)
public class MetadataDictApiServiceProvider implements MetadataDictApiService {
    private static final Logger logger = LoggerFactory.getLogger(MetadataDictApiServiceProvider.class);

    @Autowired
    MetadataDictModelService metadataDictModelService;

    @Override
    public MetadataDictModelDTO findById(Integer pk) {
        MetadataDictModel metadataDictModel = metadataDictModelService.findById(pk);

        return JSON.parseObject(metadataDictModel.toString(), MetadataDictModelDTO.class);
    }


    @Override
    public List<MetadataDictModelDTO> query(MetadataDictModelQueryDTO qp) {
        MetadataDictModel.QueryParam queryParam = JSON.parseObject(KunlongUtils.toJSONString(qp), MetadataDictModel.QueryParam.class);

        List<MetadataDictModel> metadataDictModels = metadataDictModelService.findByQueryParam(queryParam);
        return JSON.parseObject(KunlongUtils.toJSONString(metadataDictModels), List.class);

    }
    @Override
    public List<MetadataDictModelDTO> findByNotNullProps(MetadataDictModelDTO entity) {
        return null;
    }

    @Override
    public void updateNotNullPropsById(MetadataDictModelDTO entity) {

    }

}