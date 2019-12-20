package com.kunlong.platform.util;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

public class JsonUtil {

	private JsonUtil() {}
	private static ObjectMapper objMapper = new ObjectMapper();
	static {
		objMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		objMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
	}
	
	public static <T> T readValue(String value,TypeReference<T> ref) {
		try {
			return objMapper.readValue(value, ref);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public static <T> T readValue(String value,Class<T> ref) {
		try {
			return objMapper.readValue(value, ref);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	public static String write(Object obj) {
		try {
			return objMapper.writeValueAsString(obj);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
}
