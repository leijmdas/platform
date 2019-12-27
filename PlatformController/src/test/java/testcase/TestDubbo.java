package testcase;

import cn.integriti.center.api.model.SysUserDTO;
import cn.integriti.center.api.service.SysUserApiService;
import com.kunlong.api.model.DictDatatypeApiModel;
import com.kunlong.api.service.DictDataTypeApiService;
import com.kunlong.metadata.model.DictDatatype;
import com.kunlong.metadata.service.DictDataTypeService;
import com.kunlong.platform.PfControllerApp;
import com.kunlong.platform.model.DictDatatypeDemo;
import com.kunlong.platform.service.DictDatatypeServiceExample;
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
@SpringBootTest(classes = PfControllerApp.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestDubbo {
    @Autowired
    DictDatatypeServiceExample dictDatatypeServiceExample;

    @Autowired
    DictDataTypeService dictDataTypeService;

    @Reference(lazy = true, version = "1.0.0")
    SysUserApiService sysUserApiService;

    @Reference(lazy = true, version = "1.0.0")
    DictDataTypeApiService dictDataTypeApiService;


    @Before
    public void setup() {
       
    }
    
    public static void println(Object obj){
    }

    @Test
    public void test0001_service() {

        DictDatatypeDemo dictDatatypeDemo = dictDatatypeServiceExample.selectByPrimaryKey(3);
        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
        System.out.println(ret);
    }

    @Test
    public void test0002_dictDataTypeService() {

        DictDatatype dictDatatypeDemo = dictDataTypeService.selectByPrimaryKey(3);
        String ret = KunlongUtils.toJSONStringPretty(dictDatatypeDemo);
        System.out.println(ret);
    }

    //dictDataTypeApiService
    @Test
    public void test0003_dictDataTypeApiService() {
        DictDatatypeApiModel datatypeApiModel = dictDataTypeApiService.selectByPrimaryKey(3);
        String ret = KunlongUtils.toJSONStringPretty(datatypeApiModel);
        System.out.println(ret);
//        SysUserDTO sysUserDTO = sysUserApiService.findById(3);
//        String ret = KunlongUtils.toJSONStringPretty(sysUserDTO);
//        System.out.println(ret);
    }

    @Test
    public void test0004_apiLogin() throws Exception {
        sysUserApiService.checkPass(1,"admin","111111");
    }

}

