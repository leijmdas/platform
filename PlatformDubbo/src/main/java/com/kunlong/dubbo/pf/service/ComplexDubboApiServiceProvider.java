package com.kunlong.dubbo.pf.service;

import com.alibaba.fastjson.JSON;
import com.kunlong.api.model.DictDatatypeApiModel;
import com.kunlong.api.service.ComplexDictDataTypeApiService;
import com.kunlong.api.service.DictDataTypeApiService;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.annotation.Service;
import org.assertj.core.internal.bytebuddy.dynamic.TypeResolutionStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Service(interfaceClass = ComplexDictDataTypeApiService.class)

public class ComplexDubboApiServiceProvider implements ComplexDictDataTypeApiService {
    private static final Logger logger = LoggerFactory.getLogger(ComplexDubboApiServiceProvider.class);
    //@Autowired
    //DictDatatypeServiceExample dictDatatypeServiceExample;
    @Reference(lazy = true)
    DictDataTypeApiService dictDataTypeService;

    @Override
    public DictDatatypeApiModel selectByPrimaryKey(Integer datainnerid) {

        DictDatatypeApiModel dictDatatypeDemo = dictDataTypeService.selectByPrimaryKey(datainnerid);
        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
        logger.info("selectByPrimaryKey:{}",ret);
        return JSON.parseObject(ret, DictDatatypeApiModel.class);
    }
}
