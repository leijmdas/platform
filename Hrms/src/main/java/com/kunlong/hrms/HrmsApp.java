package com.kunlong.hrms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.kunlong" })
public class HrmsApp {
   public static void main(String[] args) {
       Class[] classes = new Class[]{
               HrmsApp.class
       };
       SpringApplication.run(classes, args);
   }
}
