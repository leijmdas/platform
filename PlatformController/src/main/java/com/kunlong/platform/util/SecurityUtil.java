package com.kunlong.platform.util;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class SecurityUtil {

	private SecurityUtil() {
		
	}
	public static String decodeBase64(String str) {
		Decoder decoder = Base64.getDecoder();
		byte[] bytes = decoder.decode(str.getBytes());
		return new String(bytes);
	}
	public static String encodeBase64(String str) {
		Encoder encoder = Base64.getEncoder();
		byte[] bytes = encoder.encode(str.getBytes());
		return new String(bytes);
	}
	public static String md5(String srcStr) {
		char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
        };
        try {
            byte[] btInput = srcStr.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
	}
}
