package com.kunlong.platform;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
// mvn deploy:deploy-file -DgroupId=app -DartifactId=app-support -Dversion=1.1 -Dpackaging=jar -Dfile=app-support-1.1.jar -Durl=http://localhost:8081/repository/maven-releases/ -DrepositoryId=nexus

@SpringBootApplication(scanBasePackages = {"com.kunlong"})
//@EnableDubbo
//@EnableDubboConfig
@ImportResource({"classpath:appcontext.xml"})
public class PfControllerApp {
    public static void main(String[] args) {
        Class[] classes = new Class[]{
                PfControllerApp.class
        };
        SpringApplication.run(classes, args);
    }
}
