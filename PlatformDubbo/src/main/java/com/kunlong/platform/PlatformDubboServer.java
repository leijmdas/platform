package com.kunlong.platform;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication(
        scanBasePackages = {"com.kunlong.metadata", "com.kunlong.platform", "com.kunlong.sys"}
        )
@EnableDubbo
@EnableDubboConfig
@ImportResource(locations= {"classpath:/dubbo-provider.xml","classpath:/dubbo-consumer.xml"})
public class PlatformDubboServer {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(PlatformDubboServer.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }
}
