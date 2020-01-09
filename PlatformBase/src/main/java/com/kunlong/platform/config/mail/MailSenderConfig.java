package com.kunlong.platform.config.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailSenderConfig {

	@Bean
	@ConfigurationProperties(prefix = "mail")
	public JavaMailSender mailSender() {
		org.springframework.mail.javamail.JavaMailSenderImpl sender = new JavaMailSenderImpl();
		return sender;
	}
}
