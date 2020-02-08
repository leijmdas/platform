package com.kunlong;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
@Configuration
@SpringBootApplication(scanBasePackages = {"com.kunlong"})
@EnableDubbo
@EnableDubboConfig
//@ImportResource({"classpath:appcontext.xml"})
@ImportResource(locations= {"classpath:/dubbo-provider.xml","classpath:/dubbo-consumer.xml"})
@EnableCaching
//@MapperScan(basePackages={"com.kunlong.platform.dao"})
public class PfAppServer {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PfAppServer.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }
}
