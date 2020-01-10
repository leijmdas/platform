package com.kunlong.platform.service.impl;

import com.kunlong.platform.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MailServiceImpl {

	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

	@Autowired
	private MailSender mailSender;

	public void sendEmail(String to, String subject, String content, List<String> attachFiles) {
		mailSender.sendEmail(to, subject, content, attachFiles);
	}

	public void sendEmail(String to, String subject, String content, String attachFile) {
		List<String> attachFiles = new ArrayList<>();
		attachFiles.add(attachFile);
		mailSender.sendEmail(to, subject, content, attachFiles);
	}

	public void sendEmail(String to, String subject, String content) {
		mailSender.sendEmail(to, subject, content, null);
	}

	@Service
	public static class MailSender {

		@Autowired
		private JavaMailSender javaMailSender;

		@Value("${mail.sender}")
		private String sender;

		@Value("${mail.sendernick}")
		private String sendernick;

		@Value("${mail.recipients}")
		private String recipients;

		@Async("pfThreadPool")
		public void sendEmail(String to, String subject, String content, List<String> attachFiles) {
			//System.setProperty("mail.mime.splitlongparameters","false");
			try {
				MimeMessage message = javaMailSender.createMimeMessage();
				MimeMessageHelper helper;
				helper = new MimeMessageHelper(message, true);
				helper.setFrom(sender);
				helper.setTo(to);
				helper.setSubject(subject);
				for (String fileName : attachFiles) {
					FileSystemResource file = new FileSystemResource(new File(fileName));
					helper.addAttachment(file.getFilename(), file);
					logger.info("sendEmail filename:{}",file.getFilename());
				}

				helper.setText(content, false);//true代表支持html
				javaMailSender.send(message);

				logger.info("发送HTML邮件成功");
			} catch (MessagingException e) {
				logger.error("发送HTML邮件失败：", e);
			}
		}
//
//		public void send(String subject,String content) throws MessagingException, UnsupportedEncodingException{
//			if ( this.getRecipientList() == null || this.getRecipientList().length == 0) {
//				return;
//			}
//			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
//			helper.setTo(this.getRecipientList());
//
//			helper.setFrom(new InternetAddress(String.format("%s <%s>", MimeUtility.encodeText(sendernick), sender)));
//			helper.setSubject(subject);
//			helper.setText(content);
//			javaMailSender.send(mimeMessage);
//		}

		public JavaMailSender getJavaMailSender() {
			return javaMailSender;
		}

		public void setJavaMailSender(JavaMailSender javaMailSender) {
			this.javaMailSender = javaMailSender;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public String getSendernick() {
			return sendernick;
		}

		public void setSendernick(String sendernick) {
			this.sendernick = sendernick;
		}

		public String getRecipients() {
			return recipients;
		}

		public void setRecipients(String recipients) {
			this.recipients = recipients;
		}

		public String[] getRecipientList() {
			return StringUtil.trimToEmpty(this.recipients).split(";");

		}
		
		
	}
}
