package com.kunlong.sys.support.listener;

import com.kunlong.platform.service.impl.MailServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@EnableAsync
@Component
public class JobExceptionListener implements ApplicationListener<JobExceptionEvent> {
	private static final Logger logger = LoggerFactory.getLogger(JobExceptionListener.class);

	@Autowired
	private MailServiceImpl.MailSender mailSender;

	private Map<String, Long> record = new HashMap<String, Long>();

	@Value("${job.exception.sendMsg}")
	private Boolean sendMsg;
	@Async
	@Override
	public void onApplicationEvent(JobExceptionEvent event) {
		try {
			Throwable throwable = (Throwable) event.getSource();
			String code = event.getCode();
			if (!checkRecord(code) ) {
				return;
			}
			if (sendMsg) {
				mailSender.sendEmail("to", event.getClass().getSimpleName(), this.getStackTrace(throwable), new ArrayList<String>());
			}

		} catch (Exception e) {
			logger.error("JobExceptionListener.onApplicationEvent Exception", e);
		}
	}


	private synchronized boolean checkRecord(String code) {
		String key = "key:"+code;
		Long last = record.get(key);
		if(last == null || (System.currentTimeMillis() - last)>3600*1000) {
			return true;
		}
		record.put(key, System.currentTimeMillis());
		return false;
	}

	private String getStackTrace(Throwable t) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		try {
			t.printStackTrace(pw);
			return sw.toString();
		} finally {
			pw.close();
		}
	}

}
