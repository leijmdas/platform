package com.kunlong.activemq.controller;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/activemq")
public class ActiveMQController {
//    @Autowired
//    private JmsMessagingTemplate jmsMessagingTemplate;
//
//    @RequestMapping("/send")
//    @ResponseBody
//    public String send(final String msg) {
//        jmsMessagingTemplate.convertAndSend("testQ", msg);
//        return "success";
//    }

}
