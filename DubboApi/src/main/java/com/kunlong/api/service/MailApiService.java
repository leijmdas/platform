package com.kunlong.api.service;

import java.util.ArrayList;
import java.util.List;

public interface MailApiService {
    void sendEmail(String to, String subject, String content, String attachFile);

    void sendEmail(String to, String subject, String content, List<String> attachFiles);


    void sendEmail(String to, String subject, String content);
}
