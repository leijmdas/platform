//package com.kunlong.dubbo.pf.service;
//
//import com.alibaba.fastjson.JSON;
//import com.kunlong.api.model.DictDatatypeApiModel;
//import com.kunlong.api.service.DictDataTypeApiService;
//import com.kunlong.metadata.model.DictDatatype;
//import com.kunlong.metadata.service.DictDataTypeService;
//import com.kunlong.platform.utils.KunlongUtils;
//import org.apache.dubbo.config.annotation.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
////@Service(@com.alibaba.dubbo.platform.annotation.Service(version = "${dubbo.service.version}")interfaceClass = DictDataTypeApiService.class)
//
//@Service(version = "${dubbo.service.version}",interfaceClass = DictDataTypeApiService.class)
//public class DubboApiServiceProvider implements DictDataTypeApiService {
//    private static final Logger logger = LoggerFactory.getLogger(DubboApiServiceProvider.class);
//    //@Autowired
//    //DictDatatypeServiceExample dictDatatypeServiceExample;
//    @Autowired
//    DictDataTypeService dictDataTypeService;
//
//    @Override
//    public DictDatatypeApiModel selectByPrimaryKey(Integer datainnerid) {
//
//        DictDatatype dictDatatypeDemo = dictDataTypeService.selectByPrimaryKey(datainnerid);
//        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
//        logger.info("selectByPrimaryKey:{}",ret);
//        return JSON.parseObject(ret, DictDatatypeApiModel.class);
//    }
//}
