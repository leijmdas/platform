package com.kunlong.platform.util;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理
 * 
 * @author zz
 *
 */
public class StringUtil extends StringUtils {

	/**
	 * 去除多余/
	 * 
	 * @param url
	 * @return
	 */
	public static String resolveUrl(String url) {
		Pattern p = Pattern.compile("(?<!http(s?):)/{2,}");
		return p.matcher(StringUtils.trimAllWhitespace(url)).replaceAll("/");
	}

	public static List<Integer> trans2IntegerList(String str) {
		String[] arr = str.split(",");
		List<Integer> rs = new ArrayList<Integer>();
		for (String tmp : arr) {

			rs.add(Integer.parseInt(tmp));
		}
		return rs;
	}

	// https://blog.csdn.net/qq_30024407/article/details/77099098
	public static boolean isCarNo(String carNo) {
		Pattern p = Pattern.compile(
				"^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}(?:(?![A-Z]{4})[A-Z0-9]){4}[A-Z0-9挂学警港澳]{1,}$");
		Matcher m = p.matcher(carNo);
		return m.matches();
	}

	public static String trimToEmpty(String str) {
		return StringUtils.trimAllWhitespace(str);
	}
}
