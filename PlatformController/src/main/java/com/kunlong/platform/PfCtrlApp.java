package com.kunlong.platform;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.scheduling.annotation.EnableAsync;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// mvn deploy:deploy-file -DgroupId=app -DartifactId=app-support -Dversion=1.1 -Dpackaging=jar -Dfile=app-support-1.1.jar -Durl=http://localhost:8081/repository/maven-releases/ -DrepositoryId=nexus
//http://dubbo.apache.org/

@EnableAutoConfiguration(exclude = {
        SecurityAutoConfiguration.class
})
@Configuration
@SpringBootApplication(scanBasePackages = {"com.kunlong"})
@ImportResource({"classpath:appcontext.xml"})
@EnableDubbo
@EnableDubboConfig
@EnableSwagger2
//@EnableCaching
@EnableAsync
public class PfCtrlApp {

    public static void main(String[] args) {
        //邮件附件名称不会变
        System.setProperty("mail.mime.splitlongparameters","false");
        Class[] classes = new Class[]{
                PfCtrlApp.class
        };
        SpringApplication.run(classes, args);
    }
}
