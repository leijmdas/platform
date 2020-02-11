package com.kunlong.platform.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.kunlong.platform.consts.PrimaryAppConsts;

import com.kunlong.platform.consts.SqlSessionFactoryConsts;
import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.hbatis.spring.HbatisSqlSessionDaoSupport;
import org.mybatis.hbatis.spring.HbatisSqlSessionDaoSupport;
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


@Configuration
public class PrimaryDatasourceConfig extends PrimaryAppConsts {

	@Qualifier("primaryDataSource")
	@Bean(name = "primaryDataSource", destroyMethod = "close", initMethod = "init")
	@ConfigurationProperties(prefix = "spring.datasource.platform")
	public DataSource primaryDataSource() {
		DruidDataSource druidDataSprimaryource = new DruidDataSource();
		return druidDataSprimaryource;
	}

	@Bean(name = SqlSessionFactoryConsts.SqlSessionFactory_NAME_PRIMARY)
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
	    HbatisSqlSessionDaoSupport.setHbatisSessionFactory(sessionFactory);
		return sessionFactory;
	}



	@Bean(name = "primaryMapperScannerConfigurer")
	public MapperScannerConfigurer mapperScannerConfigurer() {
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setSqlSessionFactoryBeanName(SqlSessionFactoryConsts.SqlSessionFactory_NAME_PRIMARY);
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