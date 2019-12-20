package com.kunlong.platform.util;

public class SignUtil {
	
	private SignUtil() {}
	
	public static String sign(String parkNum,String key,Long timestamp) {
		StringBuilder sb = new StringBuilder();
		sb.append(parkNum).append("|").append(timestamp).append("|").append(key);
		
		return SecurityUtil.md5(sb.toString()).toLowerCase();
	}
	
	public static void main(String[] args) {
		long timestamp = System.currentTimeMillis();
		
		String parkNum = "0001";
		
		System.out.println(timestamp);
		System.out.println(parkNum+"|"+timestamp+"|"+SignUtil.sign(parkNum,"0001", timestamp));
	}
}
