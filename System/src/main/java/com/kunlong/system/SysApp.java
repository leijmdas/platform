package com.kunlong.system;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = { "com.kunlong.system"})
@EnableSwagger2
public class SysApp {
    private static final Logger logger = LoggerFactory.getLogger(SysApp.class);

    public static void main(String[] args) {
        logger.info("start Application");
        Class[] classes = new Class[]{
                SysApp.class
        };
        SpringApplication.run(classes, args);
        logger.info("start Application");
    }
}
