package com.kunlong.platform.tasklog.config;

import com.alibaba.fastjson.JSON;
import com.kunlong.platform.dao.TasklogMapper;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.service.TasklogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Reciever {
    private CountDownLatch latch=new CountDownLatch(1);

    @Autowired
    TasklogService tasklogService;

    public void receiveMessage(String message){

        Tasklog tasklog= JSON.parseObject(message,Tasklog.class);
        tasklogService.save(tasklog);
    }

}
