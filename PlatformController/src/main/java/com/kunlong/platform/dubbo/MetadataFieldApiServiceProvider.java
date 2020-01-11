package com.kunlong.platform.dubbo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kunlong.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;
import com.kunlong.api.service.MetadataDictApiService;
import com.kunlong.api.service.MetadataFieldApiService;
import com.kunlong.platform.domain.MetadataDictModel;
import com.kunlong.platform.domain.MetadataFieldModel;
import com.kunlong.platform.service.MetadataDictModelService;
import com.kunlong.platform.service.MetadataFieldModelService;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "${dubbo.service.version}",interfaceClass = MetadataFieldApiService.class)
public class MetadataFieldApiServiceProvider implements MetadataFieldApiService {
    private static final Logger logger = LoggerFactory.getLogger(MetadataFieldApiServiceProvider.class);
    @Reference(lazy = true, version = "${dubbo.service.version}")
    MetadataDictApiService metadataDictApiService;

    @Autowired
    MetadataFieldModelService metadataFieldModelService;


    <T> List<T> transfer2Model(String text, Class<T> cls) {
        return JSON.parseArray(text,cls);

    }

    @Override
    public List<MetadataFieldModelDTO> query(MetadataFieldModelQueryDTO qp) {
        MetadataFieldModel.QueryParam queryParam = JSON.parseObject(KunlongUtils.toJSONString(qp), MetadataFieldModel.QueryParam.class);
        if (queryParam.getParam() == null) {
            queryParam.setParam(new MetadataFieldModel());
        }
//        queryParam.setLimit(-1);
//        queryParam.setStart(0);
        List<MetadataFieldModel> metadataDictModels = metadataFieldModelService.findByQueryParam(queryParam);

        return JSON.parseArray(JSON.toJSONString(metadataDictModels), MetadataFieldModelDTO.class);

    }

    @Override
    public long countByQueryParam(MetadataFieldModelQueryDTO qp) {
        MetadataFieldModel.QueryParam queryParam = JSON.parseObject(KunlongUtils.toJSONString(qp), MetadataFieldModel.QueryParam.class);
        if (queryParam.getParam() == null) {
            queryParam.setParam(new MetadataFieldModel());
        }
//        queryParam.setLimit(-1);
//        queryParam.setStart(0);
        return  metadataFieldModelService.countByQueryParam(queryParam);

    }

    @Override
    public List<MetadataFieldModelDTO> query(String tableName) {
        MetadataDictModelDTO dictModel = metadataDictApiService.findByName(tableName);

        MetadataFieldModel.QueryParam  qp=new MetadataFieldModel.QueryParam  ();
        qp.setParam(new MetadataFieldModel());
        qp.setLimit(-1);
        qp.setStart(0);
        qp.getParam().setMetadataId(dictModel.getMetadataId());
        List<MetadataFieldModel> models = metadataFieldModelService.findByQueryParam(qp);
        //return JSON.parseObject(KunlongUtils.toJSONString(models), List.class);
        return JSON.parseArray(JSON.toJSONString(models), MetadataFieldModelDTO.class);

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
