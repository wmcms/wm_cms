package com.wilson.cms.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * @ClassName DateJsonDeserializer
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 10:52
 * @Version 1.0
 **/
public class DateJsonDeserializer extends JsonDeserializer {
    public static final SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)

            throws IOException,JsonProcessingException

    {

        try

        {
            return   Timestamp.valueOf(jsonParser.getText());
          //  return format.parse();

        }

        catch(Exception e)

        {

            System.out.println(e.getMessage());

            throw new RuntimeException(e);

        }

    }
}
