package com.kunlong.platform.tasklog.service;

import com.alibaba.fastjson.JSON;
import com.kunlong.platform.domain.Tasklog;
import com.kunlong.platform.tasklog.config.RabbitConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerService {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void log(Tasklog tasklog) {
        //logger.info("tasklog:{}", tasklog);
        amqpTemplate.convertAndSend(RabbitConfig.queueName, JSON.toJSONString(tasklog));
    }
}
