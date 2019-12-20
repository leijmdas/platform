package com.kunlong.platform.util;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @name UserAgentUtil
 * @author zz  | www.xwparking.com
 * @date 2018年8月27日  
 * @description:用户终端
 */
public class UserAgentUtil {

	private UserAgentUtil() {
	}

	/**
	 * 是否为Ali
	 * @param req
	 * @return
	 */
	public static Boolean isAliClient(HttpServletRequest req) {
		String userAgent = req.getHeader("user-agent");
		return userAgent.contains("AlipayClient");
	}

	/**
	 * 是否为weixin
	 * @param req
	 * @return
	 */
	public static Boolean isWeixinClient(HttpServletRequest req) {
		String userAgent = req.getHeader("user-agent");
		return userAgent.contains("MicroMessenger");
	}

	/**
	 * 获取请求IP地址
	 * @param request
	 * @return
	 */
	public static String getRemoteAddr(HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		return ip;
	}

	/**
	 * 获取反向代理外层的访问IP地址，需反向代理正确配置X-Real-IP
	 * @param request
	 * @return
	 */
	public static String getAccessAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/** 
	 * 获取真实IP地址，不使用request.getRemoteAddr()的原因是有可能用户使用了代理软件方式避免真实IP地址, 
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值 
	 *  
	 * @param request
	 * @return ip
	 */
	public static String getRealAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
			// 多次反向代理后会有多个ip值，第一个ip才是真实ip
			if (ip.indexOf(",") != -1) {
				ip = ip.split(",")[0];
			}
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
