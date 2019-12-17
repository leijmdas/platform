package com.kunlong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@Configuration
@SpringBootApplication(scanBasePackages = {"com.kunlong"})
//@EnableDubbo
//@EnableDubboConfig
//@ImportResource({"classpath:appcontext.xml"})
public class PfApp {
    public static void main(String[] args) {
        Class[] classes = new Class[]{
                PfApp.class
        };
        SpringApplication.run(classes, args);
    }
}
