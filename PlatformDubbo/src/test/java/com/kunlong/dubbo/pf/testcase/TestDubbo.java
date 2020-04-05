package com.kunlong.dubbo.pf.testcase;

import com.alibaba.dubbo.config.annotation.Reference;
import com.kunlong.dubbo.api.dto.queryParam.MetadataFieldModelQueryDTO;
import com.kunlong.dubbo.api.model.MetadataDictModelDTO;
import com.kunlong.dubbo.api.model.MetadataFieldModelDTO;
import com.kunlong.dubbo.api.service.AuthApiService;
import com.kunlong.dubbo.api.service.MailApiService;
import com.kunlong.dubbo.api.service.MetadataDictApiService;
import com.kunlong.dubbo.api.service.MetadataFieldApiService;
import com.kunlong.dubbo.sys.service.SysUserApiService;
import com.kunlong.platform.PlatformDubboServer;
import com.kunlong.platform.dao.TasklogMapper;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.model.LoginSso;
import com.kunlong.platform.service.RedisService;
import com.kunlong.platform.utils.KunlongUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

;

/**
 * 
 * @name BaseTest
 * @author zz  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformDubboServer.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestDubbo {
    @Reference(lazy = true, version = "1.0.0")
    SysUserApiService sysUserApiService;

    @Reference(lazy = true, version = "1.0.0")
    MetadataDictApiService metadataDictApiService;
    @Reference(lazy = true, version = "1.0.0")
    MetadataFieldApiService metadataFieldApiService;

    @Reference(lazy = true, version = "${dubbo.service.version}")
    MailApiService mailApiService;
    @Reference(lazy = true, version = "${dubbo.service.version}")
    public AuthApiService authApiService;

    @Autowired
    TasklogMapper tasklogMapper;
    @Autowired
    RedisTemplate<String, LoginSso> redisTemplate;
    @Autowired
    RedisService redisService;

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
    @Test
    public void test0008_log(){
        Tasklog log=new Tasklog();
        log.setCode(0);
        log.setIp("127.0.0.1");
        log.setOprt("login");
        log.setOprtTime(new Date());
        log.setUser("ljm");
        log.setOprtType((byte)0);
        tasklogMapper.insert(log);
    }

    @Test
    public void test0009_loginSso() {
        LoginSso loginSso = new LoginSso();
        loginSso.setToken("1111");
        redisService.setLoginSso("1111", loginSso);

        System.out.println(redisService.getLoginSso("1111"));
    }

    @Test
    public void test0010_authCheck() {
        Boolean b = authApiService.checkExists("2c20e26e1a17439686f770b4706c4b68");
        System.out.println(b);
    }
}

