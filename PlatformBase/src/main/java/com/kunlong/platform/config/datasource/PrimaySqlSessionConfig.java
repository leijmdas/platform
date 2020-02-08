package com.kunlong.platform.config.datasource;

import com.kunlong.platform.consts.SqlSessionFactoryConsts;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.hbatis.spring.HbatisSqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;


@Configuration
public class PrimaySqlSessionConfig {
	
	@Bean(name = "daoSupport")
	@Lazy(value=false)
	public HbatisSqlSessionDaoSupport daoSupport(@Qualifier(SqlSessionFactoryConsts.SqlSessionFactory_NAME_PRIMARY)SqlSessionFactory sqlSessionFactory) {
		HbatisSqlSessionDaoSupport s = new HbatisSqlSessionDaoSupport(sqlSessionFactory);
		s.setSqlSessionFactory(sqlSessionFactory);
		
		return s;
	}
}
