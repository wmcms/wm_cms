package com.wilson.cms.annotation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilson.cms.utils.Constant;

import java.lang.annotation.*;
@Target({ElementType.ANNOTATION_TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER,
        ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JsonFormat(pattern = Constant.DATE_FORMAT,timezone = "GMT+8")
public @interface JsonDateFormat {
}
