package com.wilson.cms.annotation;

import java.lang.annotation.*;

/**
 * 不进行权限认证
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AllowAnonymous {
}
