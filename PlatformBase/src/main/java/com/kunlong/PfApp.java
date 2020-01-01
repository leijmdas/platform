package com.kunlong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration
@Configuration
@SpringBootApplication(scanBasePackages = {"com.kunlong"})
//@EnableDubbo
//@EnableDubboConfig
//@ImportResource({"classpath:appcontext.xml"})
@EnableCaching
public class PfApp {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(PfApp.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }
}
