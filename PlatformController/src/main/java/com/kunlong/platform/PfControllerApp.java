package com.kunlong.platform;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.kunlong"})

@EnableDubbo
@EnableDubboConfig
public class PfControllerApp {
    public static void main(String[] args) {
        Class[] classes = new Class[]{
                PfControllerApp.class
        };
        SpringApplication.run(classes, args);
    }
}
