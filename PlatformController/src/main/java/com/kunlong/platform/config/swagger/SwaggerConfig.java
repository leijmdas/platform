package com.kunlong.platform.config.swagger;

import com.google.common.collect.Sets;
import com.kunlong.platform.consts.ApiConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
//@EnableWebMvc //必须存在
public class SwaggerConfig {


//	private SecurityContext securityContext()   {
//		return SecurityContext.builder()
//				.securityReferences(defaultAuth())
//				.forPaths(PathSelectors.regex("/api/*"))
//				.build();
//	}
//
//	List<SecurityReference> defaultAuth(){
//		AuthorizationScope authorizationScope =new  AuthorizationScope("global", "accessEverything");
//		AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
//		return Arrays.asList(new SecurityReference("access-token", authorizationScopes));
//	}//securityContexts(Arrays.asList(securityContext()))
    @Bean
    public Docket createRestApi() {
    	Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .protocols(Sets.newHashSet("https", "http"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.kunlong.platform.controller.web"))
                .paths(PathSelectors.any())
                .build().securitySchemes(Arrays.asList(appApiKey(), wapApiKey(), webApiKey()));
    	return docket;
    }

	private ApiKey appApiKey() {
		return new ApiKey(ApiConstants.AUTH_API_APP, ApiConstants.AUTH_TOKEN_KEY_APP, "header");
	}

	private ApiKey wapApiKey() {
		return new ApiKey(ApiConstants.AUTH_API_WAP, ApiConstants.AUTH_TOKEN_KEY_WAP, "header");
	}

	private ApiKey webApiKey() {
		return new ApiKey(ApiConstants.AUTH_API_WEB, ApiConstants.AUTH_TOKEN_KEY_WEB, "header");
	}

	private ApiInfo apiInfo() {
    	String desc ="";
		try {
			desc = loadSimpleDescription();
		} catch (IOException e) {
			desc = "加载备注异常:"+e.getMessage();
		}
    	ApiInfo info= new ApiInfoBuilder()
                .title("API")
                .description(desc)
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    	return info;
    }
    
    private String loadSimpleDescription() throws IOException {
    	InputStream in = this.getClass().getResourceAsStream("/api-desc.html");
    	ByteArrayOutputStream result = new ByteArrayOutputStream();
    	byte[] buffer = new byte[1024];
    	int length;
    	while ((length = in.read(buffer)) != -1) {
    	    result.write(buffer, 0, length);
    	}
    	String str = result.toString(StandardCharsets.UTF_8.name());
    	return str;
    }


}