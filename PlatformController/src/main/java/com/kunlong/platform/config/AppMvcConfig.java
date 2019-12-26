package com.kunlong.platform.config;

import com.kunlong.platform.config.interceptor.AppRequestHandler;
import com.kunlong.platform.config.interceptor.WapRequestHandler;
import com.kunlong.platform.config.interceptor.WebRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class AppMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AppRequestHandler appRequestHandler;

	@Autowired
	private WapRequestHandler wapRequestHandler;

	@Autowired
	private WebRequestHandler webRequestHandler;

	private static final String[] ignorUrls = new String[] { "/public/**", "/error/**", "/auth/**" };

//	@Configuration
//	public static class WebMvcRegistrationsConfig implements WebMvcRegistrations {
//		@Override
//		public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
//			MultiVersionRequestMappingHandlerMapping handlerMapping = new MultiVersionRequestMappingHandlerMapping();
//			return handlerMapping;
//		}
//	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 多个拦截器组成一个拦截器链
		registry.addInterceptor(appRequestHandler).addPathPatterns("/app/**").excludePathPatterns("/app/auth/**", "/app/public/**");

		registry.addInterceptor(wapRequestHandler).addPathPatterns("/wap/**").excludePathPatterns("/wap/app/**", "/wap/auth/**", "/wap/public/**");
		registry.addInterceptor(webRequestHandler).addPathPatterns("/**").excludePathPatterns(concat(ignorUrls, new String[] { "/wap/**", "/app/**" })).excludePathPatterns("/swagger-resources/**",
				"/dongxw/**","/webjars/**", "/v2/**", "/swagger-ui.html/**","/rest/**");
	}

	private String[] concat(String[] res, String[] res2) {
		List<String> resList = new ArrayList<String>();
		resList.addAll(Arrays.asList(res));
		resList.addAll(Arrays.asList(res2));
		return resList.toArray(new String[] {});
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
		registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//		super.addResourceHandlers(registry);
	}

//	@Override
//	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//    	converters.forEach(c ->{
//    		if(c instanceof MappingJackson2HttpMessageConverter) {
//    			MappingJackson2HttpMessageConverter jsonC = (MappingJackson2HttpMessageConverter)c;
//    			
//    			//设置日期格式
//    	        ObjectMapper objectMapper = new ObjectMapper();
//    	        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    	        objectMapper.setDateFormat(smt);
//    	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//    	        jsonC.setObjectMapper(objectMapper);
//    	    }
//    	});
//	}


//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		//super.addCorsMappings(registry);
//		registry.addMapping("/cors/**")
//				.allowedHeaders("*")
//				.allowedMethods("POST", "GET")
//				.allowedOrigins("*");
//	}
}
