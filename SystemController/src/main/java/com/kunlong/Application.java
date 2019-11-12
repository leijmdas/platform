package com.kunlong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.kunlong.system", "com.kunlong.web"})
@EnableSwagger2
public class Application {
   public static void main(String[] args) {
       Class[] classes = new Class[]{
               Application.class
       };
       SpringApplication.run(classes, args);
   }
}
