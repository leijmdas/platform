package com.kunlong.platform.tasklog.aspect;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLoggerAnnotation {
    String value();
    boolean security() default false;
}
