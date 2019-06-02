package com.wilson.cms.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.JsonSerializer;

import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.text.SimpleDateFormat;

/**
 * @ClassName DateJsonSerializer
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 10:57
 * @Version 1.0
 **/
public class DateJsonSerializer extends JsonSerializer {
    public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Object date, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(format.format(date));
    }
}
