package com.wilson.cms.config;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;


/**
 * @ClassName StringToDate
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 10:44
 * @Version 1.0
 **/
public class StringToDate implements Converter<String, Timestamp> {
    @Override
    public Timestamp convert(String s) {

        Timestamp sql = Timestamp.valueOf(s);

        return sql;
    }

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }


}