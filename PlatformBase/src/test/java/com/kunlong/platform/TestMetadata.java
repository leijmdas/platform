package com.kunlong.platform;

import com.kunlong.PfApp;
import com.kunlong.platform.dao.DictConfigMapper;
import com.kunlong.platform.dao.MetadataFieldModelMapper;
import com.kunlong.platform.domain.MetadataFieldModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * 
 * @name BaseTest
 * @author zz  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfApp.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestMetadata {

    @Autowired
    MetadataFieldModelMapper metadataFieldModelMapper;

    @Autowired
    DictConfigMapper dictConfigMapper;

    @Before
    public void setup() {
       
    }
    
    public static void println(Object obj){
    }

       @Test
    public void test0001_service() {

        MetadataFieldModel metadataField = metadataFieldModelMapper.selectByPK(11);
        //String ret = KunlongUtils.toJSONStringPretty(metadataField);
        System.out.println(metadataField);
    }

    @Test
    public void test0002_service() {
        MetadataFieldModel.QueryParam queryParam=new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(59);
        List<MetadataFieldModel> metadataFields = metadataFieldModelMapper.findByQueryParam(queryParam);
        //String ret = KunlongUtils.toJSONStringPretty(metadataField);
        System.out.println(metadataFields);
    }

}

