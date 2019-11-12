package com.kunlong;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

//@SpringBootApplication(scanBasePackages = {"com.kunlong.activemq", "com.kunlong.platform"})

@SpringBootApplication(scanBasePackages = {"com.kunlong.web"},exclude ={DataSourceAutoConfiguration.class})
@EnableSwagger2
@EnableDubbo
public class SysGatewayApp {
   public static void main(String[] args) {
       Class[] classes = new Class[]{
               SysGatewayApp.class
       };
       SpringApplication app=new SpringApplication(SysGatewayApp.class);
       app.setBannerMode(Banner.Mode.OFF);
       app.run(args);
       //SpringApplication.run(classes, args);
   }
}
