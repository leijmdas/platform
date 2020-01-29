package com.kunlong.platform.config.auth;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PreAuthAnnotation {
    String value();
}
