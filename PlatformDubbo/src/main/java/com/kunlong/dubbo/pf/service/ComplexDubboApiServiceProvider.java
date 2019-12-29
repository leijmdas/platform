//package com.kunlong.dubbo.pf.service;
//
//import com.kunlong.api.model.DictDatatypeApiModel;
//import com.kunlong.api.service.ComplexDictDataTypeApiService;
//import com.kunlong.api.service.DictDataTypeApiService;
//import com.kunlong.platform.utils.KunlongUtils;
//import org.apache.dubbo.config.annotation.Reference;
//import org.apache.dubbo.config.annotation.Service;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//@Service(interfaceClass = ComplexDictDataTypeApiService.class)
//public class ComplexDubboApiServiceProvider implements ComplexDictDataTypeApiService {
//    private static final Logger logger = LoggerFactory.getLogger(ComplexDubboApiServiceProvider.class);
//
//    //@Autowired
//    //DictDatatypeServiceExample dictDatatypeServiceExample;
//
//    //@Reference(lazy = true, version = "${dubbo.service.version}")
//    //DictDataTypeApiService dictDataTypeService;
//
//    @Override
//    public DictDatatypeApiModel selectByPrimaryKey(Integer datainnerid) {
//
////        DictDatatypeApiModel dictDatatypeApiModel = dictDataTypeService.selectByPrimaryKey(datainnerid);
////        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeApiModel);
////        logger.info("selectByPrimaryKey:{}",ret);
////        return  KunlongUtils.parseObject(ret, DictDatatypeApiModel.class);
//    }
//}
