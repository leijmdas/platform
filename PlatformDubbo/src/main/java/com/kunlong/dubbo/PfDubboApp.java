package com.kunlong.dubbo;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kunlong.dubbo.pf", "com.kunlong", "com.kunlong.platform"})

@EnableDubbo
@EnableDubboConfig
public class PfDubboApp {
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(PfDubboApp.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }
}
