package com.kunlong.platform.config;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AppCorsFilter extends OncePerRequestFilter {

	protected final Log logger = LogFactory.getLog(getClass());
//	private static String ALLOW_HEADERS = "Origin, X-Requested-With, Content-Type, Accept";

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		// CORS "pre-flight" request
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "*, access-token, xw-token, app-version, Content-Type");
		response.addHeader("Access-Control-Max-Age", "1800");// 30 min
		if(request.getMethod().toUpperCase().equals("OPTIONS")){
			
			return ;
		}
		// This will filter your requests and responses.
		filterChain.doFilter(request, response);
	}
}
