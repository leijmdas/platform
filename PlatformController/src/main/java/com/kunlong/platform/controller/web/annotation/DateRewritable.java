package com.kunlong.platform.controller.web.annotation;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
/**
 * 日期重写
 * @name DateRewritable
 * @author zz  | www.xwparking.com
 * @date 2018年12月19日  
 * @description:
 */
public @interface DateRewritable {

	String value() default "yyyy-MM-dd";
}
