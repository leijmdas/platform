package com.kunlong.systemcontrllort.testcase;

import com.kunlong.SysGatewayApp;
import com.kunlong.api.model.DictDatatypeApiModel;
import com.kunlong.api.service.DictDataTypeApiService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 
 * @name BaseTest
 * @author zz  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysGatewayApp.class)
@WebAppConfiguration
public class TestPfDubboService {
    @Reference
    DictDataTypeApiService dictDataTypeApiService;

    @Before
    public void setup() {
       
    }
    
    public static void println(Object obj){
    }

    @Test
    public void test0001_dubboService(){
        DictDatatypeApiModel dictDatatypeApiModel=dictDataTypeApiService.selectByPrimaryKey(3);
        System.out.println(dictDatatypeApiModel);
    }
}

