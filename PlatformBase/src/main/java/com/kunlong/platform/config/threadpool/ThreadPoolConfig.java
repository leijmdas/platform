package com.kunlong.platform.config.threadpool;


import com.kunlong.platform.model.KunlongModel;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


 /**
 * Created by ljm on 2017/8/30.
 */
 @Component
 @ConfigurationProperties(prefix = "threadpool")
 public class ThreadPoolConfig extends KunlongModel {


     //线程池信息
     private int corePoolSize;
     private int maxPoolSize;
     private int keepAliveSeconds;
     private int queueCapacity;


     public int getCorePoolSize() {
         return corePoolSize;
     }

     public void setCorePoolSize(int corePoolSize) {
         this.corePoolSize = corePoolSize;
     }

     public int getMaxPoolSize() {
         return maxPoolSize;
     }

     public void setMaxPoolSize(int maxPoolSize) {
         this.maxPoolSize = maxPoolSize;
     }

     public int getKeepAliveSeconds() {
         return keepAliveSeconds;
     }

     public void setKeepAliveSeconds(int keepAliveSeconds) {
         this.keepAliveSeconds = keepAliveSeconds;
     }

     public int getQueueCapacity() {
         return queueCapacity;
     }

     public void setQueueCapacity(int queueCapacity) {
         this.queueCapacity = queueCapacity;
     }



}