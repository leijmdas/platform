package com.kunlong.platform.dubbo;

import com.alibaba.fastjson.JSON;
import com.kunlong.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;
import com.kunlong.api.service.MetadataFieldApiService;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataFieldModelService;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}",interfaceClass = MetadataFieldApiService.class)
public class MetadataFieldApiServiceProvider implements MetadataFieldApiService {
    private static final Logger logger = LoggerFactory.getLogger(MetadataFieldApiServiceProvider.class);

    @Autowired
    MetadataFieldModelService metadataFieldModelService;
    @Override
    public List<MetadataFieldModelDTO> query(MetadataFieldModelQueryDTO qp) {
        MetadataFieldModel.QueryParam queryParam = JSON.parseObject(KunlongUtils.toJSONString(qp), MetadataFieldModel.QueryParam.class);

        List<MetadataFieldModel> metadataDictModels = metadataFieldModelService.findByQueryParam(queryParam);
        return JSON.parseObject(KunlongUtils.toJSONString(metadataDictModels), List.class);

    }
    @Override
    public MetadataFieldModelDTO findById(Integer pk) {
        MetadataFieldModel metadataFieldModel = metadataFieldModelService.findById(pk);

        return JSON.parseObject(metadataFieldModel.toString(), MetadataFieldModelDTO.class);
    }

    @Override
    public List<MetadataFieldModelDTO> findByNotNullProps(MetadataFieldModelDTO entity) {
        return null;
    }

    @Override
    public void updateNotNullPropsById(MetadataFieldModelDTO entity) {

    }


}
