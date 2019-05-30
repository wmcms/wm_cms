package com.wilson.cms.annotation;

import java.lang.annotation.*;

/**
 * 权限标记
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Permission {
    String value();
}
