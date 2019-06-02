package com.wilson.cms.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class ConvertBean implements  WebMvcConfigurer {

    public Converter<String, Timestamp> toTimesConvert() {
        return new Converter<String, Timestamp>() {
            @Override
            public Timestamp convert(String s) {
                Timestamp sql = Timestamp.valueOf(s);

                return sql;
            }
        };
    }

    public Converter<String, Date> toTimestampConvert() {
        return new Converter<String, Date>() {
            @Override
            public Date convert(String source) {
                Date date = null;
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try{
                 date=   simpleDateFormat.parse(source);
                }catch (ParseException e){
                   e.printStackTrace();
                }
                return date;
            }
        };
    }

}
