package com.kunlong.platform;

import com.kunlong.PfAppServer;
import com.kunlong.platform.config.threadpool.TaskExecutePool;
import com.kunlong.platform.config.threadpool.ThreadPoolConfig;
import com.kunlong.platform.context.AppKlongContext;
import com.kunlong.platform.dao.*;
import com.kunlong.platform.domain.MetadataFieldModel;

import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.service.RedisService;
import com.kunlong.platform.service.impl.MailServiceImpl;
import com.kunlong.sysuser.dao.SysUserModelMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

/**
 * 
 * @name BaseTest
 * @author ljm  | www.ll.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PfAppServer.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestMetadata {
    @Resource
    MailServiceImpl mailService;

    @Resource
    CacheManager cacheManager;

    @Autowired
    TasklogMapper tasklogMapper;

    @Autowired
    MetadataFieldModelMapper metadataFieldModelMapper;

    @Autowired
    DictConfigMapper dictConfigMapper;

    @Autowired
    ThreadPoolConfig threadPoolConfig;

    @Autowired
    TaskExecutePool taskExecutePool;

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
        MetadataFieldModel.QueryParam queryParam = new MetadataFieldModel.QueryParam();
        queryParam.setParam(new MetadataFieldModel());
        queryParam.getParam().setMetadataId(59);
        List<MetadataFieldModel> metadataFields = metadataFieldModelMapper.findByQueryParam(queryParam);
        //String ret = KunlongUtils.toJSONStringPretty(metadataField);
        System.out.println(metadataFields);
    }

    @Test
    public void test0003_cache() {

        Cache cache = cacheManager.getCache("PlatformCache");
        cache.put("key", "123");
        String key = cache.get("key", String.class);
        System.out.println(key);


    }

    @Test
    public void test0004_sendMailServiceImpl() {
        mailService.sendEmail("leijmdas_s@163.com", "test", "test");

    }

    @Test
    public void test0005_sendMailServiceAtrtach() {
        mailService.sendEmail("leijmdas_s@163.com", "test", "test", "c:/1.png");

    }

    @Test
    public void test0006_threadPoolConfig() {
        System.out.println(threadPoolConfig);
        Executor executor = AppKlongContext.getAppCtxt().getBean("pfThreadPool", Executor.class);
        //executor.execute();
        System.out.println(executor);

    }

    @Test
    public void test0007_log(){
        Tasklog log=new Tasklog();
        log.setCode(0);
        log.setIp("127.0.0.1");
        log.setOprt("login");
        log.setOprtTime(new Date());
        log.setUser("ljm");
        log.setOprtType((byte)0);
        tasklogMapper.insert(log);
    }

    @Autowired
    RedisDAO redisDAO;

    @Autowired
    RedisService redisService;
    @Test
    public void test0008_redisDao() {
        redisDAO.setKey("a", "1");
        System.out.println(redisDAO.getKey("a"));

    }

    @Test
    public void test0008_redisService() {
        redisService.setKey("a1", "111");
        System.out.println(redisService.getKey("a1"));

    }

//    @Autowired
//    TaskMapper taskMapper;
//
//    @Test
//    public void test0009() {
//        Example example = new Example(Tasklog.class);
//
//        int count = taskMapper.selectCountByExample(example);
//
//        RowBounds rowBounds = new RowBounds(0, 3);
//
//        List<Tasklog> list = taskMapper.selectByExampleAndRowBounds(example, rowBounds);
//        System.out.println(list);
//    }

    @Autowired
    SysUserModelMapper sysUserModelMapper;
    @Test
    public void test0009_sysuSerRoleModel(){
        sysUserModelMapper.getUserByUserName("ljm");
    }
}

