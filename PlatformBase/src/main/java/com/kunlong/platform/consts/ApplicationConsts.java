package com.kunlong.platform.consts;
/**
 * 
 * @className: ApplicationConsts  
 * @description: 应用基本配置常量 
 * @author zz  | www.integriti.cn
 * @date 2018年4月18日  
 *
 */
public class ApplicationConsts {

	
	protected static String MYBATIS_MAPPER_LOCATIONS = "classpath:/mapper/sys/*.xml";
	
	protected static String MYBATIS_BASE_PACKAGE = "com.kunlong.sys.dao,org.mybatis.hbatis.orm.mapper";
	
	
	protected static String QUARTZ_SCHEDULE_NAME = "C";
	
	protected static String QUARTZ_CONFIG_LOCATION = "classpath:quartz.properties";
	
}
