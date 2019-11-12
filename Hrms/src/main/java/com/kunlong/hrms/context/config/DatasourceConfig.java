package com.kunlong.hrms.context.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import org.mybatis.hbatis.spring.HbatisSqlSessionDaoSupport;

@Configuration( "hrmsDatasourceConfig" )
public class DatasourceConfig extends ApplicationConsts {

	@Qualifier("hrmsDataSource")
	@Bean(name = "hrmsDataSource")
	//@Bean(name = "primaryDataSource", destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.hrms")
	public DataSource hrmsDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Bean(name = "hrmsSqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("hrmsDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		List<Resource> resList = new ArrayList<Resource>();
		for(String location:MYBATIS_MAPPER_LOCATIONS) {
			resList.addAll(Arrays.asList(resolver.getResources(location)));
			
		}
		bean.setMapperLocations(resList.toArray(new Resource[0]));
		
		SqlSessionFactory sessionFactory = bean.getObject();
		sessionFactory.getConfiguration().setMapUnderscoreToCamelCase(true);
		return sessionFactory;
	}


	@Bean(name = "hrmsMapperScannerConfigurer")public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("hrmsSqlSessionFactory");
		mapperScannerConfigurer.setBasePackage(MYBATIS_BASE_PACKAGE);

		return mapperScannerConfigurer;
	}

	@Bean(name = "hrmsTransactionManager")
	@Primary
	public DataSourceTransactionManager hrmsTransactionManager(
			@Qualifier("hrmsDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}