package testcase;

import cn.integriti.center.api.service.SysUserApiService;
import com.kunlong.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.api.model.MetadataDictModelDTO;
import com.kunlong.api.model.MetadataFieldModelDTO;
import com.kunlong.api.service.MailApiService;
import com.kunlong.api.service.MetadataDictApiService;
import com.kunlong.api.service.MetadataFieldApiService; ;
import com.kunlong.platform.PfCtrlApp;
import com.kunlong.platform.utils.KunlongUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@SpringBootTest(classes = PfCtrlApp.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestDubbo {
    @Reference(lazy = true, version = "1.0.0")
    SysUserApiService sysUserApiService;

    @Reference(lazy = true, version = "1.0.0")
    MetadataDictApiService metadataDictApiService;
    @Reference(lazy = true, version = "1.0.0")
    MetadataFieldApiService metadataFieldApiService;

    @Reference(lazy = true, version = "${dubbo.service.version}")
    MailApiService mailApiService;

    @Before
    public void setup() {
       
    }

    @Test
    public void test0001_sapiLogin() {
       sysUserApiService.checkPass(1,"admin","123456");

    }


    @Test
    public void test0002_MetadataDictApiService_findById() throws Exception {
        MetadataDictModelDTO metadataDictModelDTO = metadataDictApiService.findById(59);
        System.out.println(metadataDictModelDTO);
    }
    @Test
    public void test0003_MetadataDictApiService_queryParam() throws Exception {
        MetadataFieldModelQueryDTO queryDTO = new MetadataFieldModelQueryDTO();
        queryDTO.setParam(new MetadataFieldModelDTO());
        queryDTO.getParam().setMetadataId(59);
        List<MetadataFieldModelDTO> list = metadataFieldApiService.query(queryDTO);
        System.out.println(KunlongUtils.toJSONStringPretty(list));
    }

    @Test
    public void test0004_MetadataFieldApiService_queryParam() throws Exception {
        MetadataFieldModelQueryDTO queryDTO = new MetadataFieldModelQueryDTO();
        queryDTO.setParam(new MetadataFieldModelDTO());
        queryDTO.getParam().setMetadataId(59);
        List<MetadataFieldModelDTO> list = metadataFieldApiService.query(queryDTO);
        System.out.println(KunlongUtils.toJSONStringPretty(list));
    }


    @Test
    public void test0005() {
        MetadataDictModelDTO  modelDTO = metadataDictApiService.findByName("dict_area");
        System.out.println(modelDTO);

    }
    @Test
    public void test0006() {
        List<MetadataFieldModelDTO>  models = metadataFieldApiService.query("dict_area");
        System.out.println(models);

    }

    @Test
    public void  test0007_sendMailServiceAtrtach()
    {
        mailApiService.sendEmail("leijmdas_s@163.com","sub","test","c:/1.png");

    }
}

