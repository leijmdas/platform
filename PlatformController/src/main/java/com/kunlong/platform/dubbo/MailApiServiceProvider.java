package com.kunlong.platform.dubbo;

import com.kunlong.api.service.MailApiService;
import com.kunlong.platform.service.impl.MailServiceImpl;
import org.apache.dubbo.config.annotation.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;


@Service(version = "${dubbo.service.version}", interfaceClass = MailApiService.class)
public class MailApiServiceProvider implements MailApiService {
    private static final Logger logger = LoggerFactory.getLogger(MailApiServiceProvider.class);

    @Resource
    MailServiceImpl mailService;


    @Override
    public void sendEmail(String to, String subject, String content, String attachFile) {
        mailService.sendEmail(to, subject, content, attachFile);
    }

    @Override
    public void sendEmail(String to, String subject, String content, List<String> attachFiles) {
        mailService.sendEmail(to, subject, content, attachFiles);

    }

    @Override
    public void sendEmail(String to, String subject, String content) {
        mailService.sendEmail(to, subject, content);

    }

}
