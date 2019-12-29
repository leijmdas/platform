package com.kunlong.web.system.testcase;


import com.kunlong.platform.utils.KunlongUtils;
import com.kunlong.system.SysApp;

import com.kunlong.system.model.DictDatatype;
import com.kunlong.system.service.DictDataTypeService;
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
@ComponentScan(basePackages={"com.kunlong.system","com.kunlong.platform"})
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SysApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestDictDatatypeService extends TestCase {

    @Autowired


	DictDataTypeService dictDataTypeService;

	@Override
	protected void setUp() throws Exception {

	}

	@Test
	public void test0001_s() throws Exception {
		System.out.println(1);
		List<DictDatatype> result = dictDataTypeService.selectDatatype();
		System.out.println(KunlongUtils.toJSONStringPretty(result));
	}

	@Test
	public void test0002_selectAll() throws Exception {
		System.out.println(1);
		//List<DictDatatypeDemo> result = dictDatatypeServiceExample.selectAll();

	}

	@Test
	public void test0003_selectOne() throws Exception {

		//DictDatatypeDemo result = dictDatatypeServiceExample.selectOne(dictDatatypeDemo);
		//System.out.println(KunlongUtils.toJSONStringPretty(result));
		//System.out.println(dictDatatypeServiceExample.getClass().getName());
	}
}






