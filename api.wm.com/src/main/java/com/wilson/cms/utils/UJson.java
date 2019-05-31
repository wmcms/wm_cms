package com.wilson.cms.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class UJson {
	private static ObjectMapper objectMapper = new ObjectMapper();

	//对象转字符串
	public static <T> String obj2String(T obj){
		if (obj == null){
			return null;
		}
		try {
			return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	//字符串转对象
	public static <T> T string2Obj(String str,Class<T> clazz){
		if (StringUtils.isEmpty(str) || clazz == null){
			return null;
		}
		try {
			return clazz.equals(String.class)? (T) str :objectMapper.readValue(str,clazz);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
