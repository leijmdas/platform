package com.kunlong.platform.config.fastdfs;


import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ConfigurationProperties(prefix = "fastdfs")
@Lazy(value = false)
public class FastDfsConfig {

	private Properties props;

	
	public Properties getProps() {
		return props;
	}


	public void setProps(Properties props) {
		this.props = props;
	}


	@PostConstruct
	public void init() {

		try {

			ClientGlobal.initByProperties(props);
		} catch (IOException | MyException e) {
			throw new RuntimeException("加载fastdfs配置异常:" + e.getMessage(), e);
		}

	}
}
