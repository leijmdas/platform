package com.kunlong.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com.kunlong.activemq", "com.kunlong.platform"})
@EnableSwagger2
public class PfApp {
    public static void main(String[] args) {
        Class[] classes = new Class[]{
                PfApp.class
        };
        SpringApplication.run(classes, args);
    }
}
