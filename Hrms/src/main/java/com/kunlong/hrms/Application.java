package com.kunlong.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.kunlong" })
public class Application {
   public static void main(String[] args) {
       Class[] classes = new Class[]{
               Application.class
       };
       SpringApplication.run(classes, args);
   }
}
