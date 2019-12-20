package com.kunlong.platform.controller.web;

import app.support.query.PageQueryParam;
import app.support.query.PageQueryParam.DateRange;
import com.kunlong.platform.controller.web.annotation.DateRewritable;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

@ControllerAdvice(basePackages= {"com.xw.ipark.manager"})

/**
 * 分页请求参数处理
 * @name PageQueryParamRequestBodyAdvice
 * @author zz  | www.xwparking.com
 * @date 2018年12月19日  
 * @description:
 */
public class PageQueryParamRequestBodyAdvice implements RequestBodyAdvice {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		boolean flag = methodParameter.hasParameterAnnotation(RequestBody.class);
		
		if(!flag) {
			return flag;
		}
		if(ParameterizedType.class.equals(targetType)) { //如果是泛型
			ParameterizedType pt = (ParameterizedType)targetType;
			Type t = pt.getActualTypeArguments()[0];
			if(targetType instanceof Class && isPageQueryParam((Class)t)) {
				return true;
			}
			return false;
			
		} else if(targetType instanceof Class) {
			return isPageQueryParam((Class)targetType);
		}
		return false;
	}
	boolean isPageQueryParam(@SuppressWarnings("rawtypes") Class clazz) {
		return PageQueryParam.class.isAssignableFrom(clazz);
	}
	@Override
	public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
		return inputMessage;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		if(body == null) {
			return null;
		}
		if(parameter.hasParameterAnnotation(DateRewritable.class)) {
			return postHandlePageQueryParam((PageQueryParam)body);
		}
		return body;
	}

	@Override
	public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return body;
	}

	
	public PageQueryParam<?> postHandlePageQueryParam(PageQueryParam<?> qp) {
		Map<String, DateRange> qpDRMap = qp.getDateRanges();
		if(qpDRMap != null) {
			for( Map.Entry<String, DateRange> entry:qpDRMap.entrySet()) {
				if(entry.getValue() != null) {
					if(entry.getValue().getEndDate() != null) { //加一天
						entry.getValue().setEndDate(addOneDay(entry.getValue().getEndDate()));
					}
				}
			}
		}
		return qp;
	}
	
	private Date addOneDay(Date d) {
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 1);
		
		return c.getTime();
	}
}
