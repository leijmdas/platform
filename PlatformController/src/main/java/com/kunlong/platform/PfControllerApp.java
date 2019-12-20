package com.kunlong.platform;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


// mvn deploy:deploy-file -DgroupId=app -DartifactId=app-support -Dversion=1.1 -Dpackaging=jar -Dfile=app-support-1.1.jar -Durl=http://localhost:8081/repository/maven-releases/ -DrepositoryId=nexus


@EnableAutoConfiguration
@Configuration
@SpringBootApplication(scanBasePackages = {"com.kunlong","cn.integriti.center"})
@ImportResource({"classpath:appcontext.xml"})
public class PfControllerApp {
    private static final Logger logger = LoggerFactory.getLogger(PfControllerApp.class);


    public static void main(String[] args) {
        logger.info("appcontext PfControllerApp appcontext appcontext");
        Class[] classes = new Class[]{
                PfControllerApp.class
        };
        SpringApplication.run(classes, args);
    }
}
