package com.kunlong.platform.util;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class RestUtil {

	private RestTemplate restTpl;

	public static class Singleton {
		static RestUtil instance;

		public static void instanceRestTemplate(RestTemplate tpl) {
			instance = new RestUtil(tpl);
		}

	}

	public RestUtil(RestTemplate tpl) {
		this.restTpl = tpl;
	}
	public static RestUtil getInstance() {
		return Singleton.instance;
	}
	private HttpHeaders createDefaultHeader() {
		HttpHeaders headers = new HttpHeaders();
	    MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
	    headers.setContentType(type);
	    return headers;
	}
	private HttpHeaders createFormHeader() {
		HttpHeaders headers = new HttpHeaders();
	    MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
	    headers.setContentType(type);
	    return headers;
	}
	private String prehandleUri(String url,Map<String,Object> params) {
		StringBuilder sb = new StringBuilder();
		if(params != null) {
			for(Map.Entry<String, Object> entry:params.entrySet()) {
				sb.append("&").append(entry.getKey()).append("={").append(entry.getKey()).append("}");
			}
		} 
		String _uri = (sb.length()>0?("?"+sb.substring(1)):"");
		
		return url +_uri;
	}
	public <T> T postJSON(String url,Object data,ParameterizedTypeReference<T> rspRef) {
		
		ResponseEntity<T> rsp = this.restTpl.exchange(url, HttpMethod.POST, new HttpEntity<Object>(data,createDefaultHeader()), rspRef);
		
		return rsp.getBody();
	}
	public <T> T postJSON(String url,Object data,Class<T> clazz) {
		ResponseEntity<T> rsp = this.restTpl.exchange(url, HttpMethod.POST, new HttpEntity<Object>(data,createDefaultHeader()), clazz);
		return rsp.getBody();
	}
	public <T> T post(String url,Map<String,Object> params,ParameterizedTypeReference<T> rspRef) {
		String _url = prehandleUri(url,params);
		ResponseEntity<T> rsp = this.restTpl.exchange(_url, HttpMethod.POST, new HttpEntity<Object>(params,createFormHeader()), rspRef, params == null?new HashMap<String,Object>():params);
		return rsp.getBody();
	}
	public <T> T post(String url,Map<String,Object> params,Class<T> clazz) {
		String _url = prehandleUri(url,params);
		ResponseEntity<T> rsp = this.restTpl.exchange(_url, HttpMethod.POST, new HttpEntity<Object>(null,createFormHeader()), clazz, params == null?new HashMap<String,Object>():params);
		return rsp.getBody();
	}
	
	
	public <T> T get(String url,Map<String,Object> params,Class<T> clazz) {
		String _url = prehandleUri(url,params);
		ResponseEntity<T> rsp = this.restTpl.getForEntity(_url, clazz, params == null?new HashMap<String,Object>():params);
		return rsp.getBody();
	}
	public <T> T get(String url,Map<String,Object> params,ParameterizedTypeReference<T> rspRef) {
		String _url = prehandleUri(url,params);
		ResponseEntity<T> rsp = this.restTpl.exchange(_url, HttpMethod.GET, new HttpEntity<Object>(null), rspRef, params == null?new HashMap<String,Object>():params);
		return rsp.getBody();
	}
}
