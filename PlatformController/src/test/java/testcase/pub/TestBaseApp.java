package testcase.pub;

import com.jtest.testframe.ITestImpl;
import com.jtest.utility.testlog.TestLog;
import com.kunlong.metadata.service.MetadataDictService;
import com.kunlong.platform.PfCtrlApp;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.net.URL;

/**
 * 
 * @name BaseTest
 * @author ljm  | www.xwparking.com
 * @date 2018年11月23日  
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PfCtrlApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class TestBaseApp extends ITestImpl {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;

    public void addLink(String userId, URL url) {
        listOps.leftPush(userId, url.toExternalForm());
        redisTemplate.boundListOps(userId).leftPush(url.toExternalForm());
    }

    @Before
    public void setup() {

    }

    @Test
    public void testRedis() {
        redisTemplate.opsForValue().set("stringValue","bbb");
        TestLog.logJtest(redisTemplate.opsForValue().get("stringValue"));

    }

    @Autowired
    MetadataDictService metadataDictService;

    @Test
    public void checkTableExist() {
        Boolean ret = metadataDictService.checkTableExists("ytb_manager", "dict_area");
        TestLog.logJtest(ret);
    }

}
