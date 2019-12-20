package com.kunlong.platform.consts;

/**
 * API常量
 * @author zz
 *
 */
public class ApiConstants {

	private ApiConstants(){}

	/**
	 * 用在API注解上 APP
	 */
	public static final String AUTH_API_APP = "app";

	/**
	 * 用在API注解上 WAP
	 */
	public static final String AUTH_API_WAP = "wap";

	/**
	 * 用在API注解上 WEB
	 */
	public static final String AUTH_API_WEB = "api";
	
	/**
	 * 认证TOKEN KEY APP
	 */
	public static final String AUTH_TOKEN_KEY_APP = "XW-TOKEN";
	
	/**
	 * 认证TOKEN KEY WAP
	 */
	public static final String AUTH_TOKEN_KEY_WAP = "XW-TOKEN";
	
	/**
	 * 认证TOKEN KEY WEB
	 */
	public static final String AUTH_TOKEN_KEY_WEB = "ACCESS-TOKEN";
	
	public static final String PREFIX_IPARK = "/ipark";
	
	public static final String PREFIX_SYS = "/sys";
	
	public static final String PREFIX_APP = "/app";
	
	public static final String PREFIX_WAP = "/wap";
}
