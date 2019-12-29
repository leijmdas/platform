package com.kunlong.dubbo.pf.testcase;

import com.kunlong.api.model.DictDatatypeApiModel;
import com.kunlong.api.service.DictDataTypeApiService;
import com.kunlong.dubbo.PfDubboApp;
import com.kunlong.metadata.model.DictDatatype;
import com.kunlong.metadata.service.DictDataTypeService;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 
 * @name BaseTest
 * @author zz  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfDubboApp.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestPfServiceImpl {
    @Autowired
    DictDataTypeService dictDataTypeService;

    @Reference(lazy = true, version = "${dubbo.service.version}")
    DictDataTypeApiService dictDataTypeApiService;



    @Before
    public void setup() {
       
    }
    
    public static void println(Object obj){
    }

    @Test
    public void test0001_service() {

    }

    @Test
    public void test0002_dictDataTypeService() {

        DictDatatype dictDatatypeDemo = dictDataTypeService.selectByPrimaryKey(3);
        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
        System.out.println(ret);
    }

    @Test
    public void test0003_dictDataTypeApiService() {

        DictDatatypeApiModel dictDatatypeDemo = dictDataTypeApiService.selectByPrimaryKey(3);
        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
        System.out.println(ret);
    }

}

