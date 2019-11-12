package com.kunlong.system.context.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.hbatis.spring.HbatisSqlSessionDaoSupport;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
// location =“classpath：/ properties / $ {spring.profiles .active：test} /some.properties“/>

@Configuration( "systemDatasourceConfig" )
@PropertySource({"classpath:/datasource/datasource-${spring.profiles.active}.properties"})
//@PropertySource({"classpath:/datasource/datasource-dev.properties"})
public class DatasourceConfig extends ApplicationConsts {

	@Qualifier("primaryDataSource")
	@Bean(name = "primaryDataSource")
	//@Bean(name = "primaryDataSource", destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.system")
	public DataSource primaryDataSource() {
		DruidDataSource druidDataSource = new DruidDataSource();
		return druidDataSource;
	}

	@Bean(name = "primarySqlSessionFactory")
	@Primary
	public SqlSessionFactory primarySqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource)
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
		//HbatisSqlSessionDaoSupport.setHbatisSessionFactory(sessionFactory);
		return sessionFactory;
	}


	@Bean(name = "systemMapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName("primarySqlSessionFactory");
		mapperScannerConfigurer.setBasePackage(MYBATIS_BASE_PACKAGE);

		return mapperScannerConfigurer;
	}

	@Bean(name = "primaryTransactionManager")
	@Primary
	public DataSourceTransactionManager primaryTransactionManager(
			@Qualifier("primaryDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}
}