package com.kunlong.tasklog.testcase;

import com.kunlong.tasklog.Application;
import com.kunlong.tasklog.context.KunlongUtils;
import com.kunlong.tasklog.model.BBNoModel;
import com.kunlong.tasklog.service.BBNoService;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages={"com.kunlong.tasklog"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestBbNoService extends TestCase {

    @Autowired
	BBNoService bbNoService;
	@Override
	protected void setUp() throws Exception {

	}

	@Test
	public void test0001() throws Exception {
		System.out.println(1);
        List<BBNoModel> result = bbNoService.selectBBNo();
        System.out.println(KunlongUtils.toJSONStringPretty(result));
    }
	

}
