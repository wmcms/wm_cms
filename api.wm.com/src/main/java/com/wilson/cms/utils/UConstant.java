package com.wilson.cms.utils;

import java.security.MessageDigest;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class UConstant {

	public  static final String DATE_FORMAT="yyyy-MM-dd";
	public  static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	public  static final String STR_DATE_TIME_FORMAT="yyyyMMddHHmmss";
	/** 空字符串 */
	public static final String NULLSTR = "";

	/** 下划线 */
	public static final char SEPARATOR = '_';

	public  static final  String LONIN_COOKIE_NAME="AUTHENTICATION_TOKEN";
	public  static final  String X_API_TOKEN="x-api-token";

	public  static  final  Integer CODE_SUCCESS=200;
	public  static  final  Integer CODE_ERROR=405;
	public  static  final  Integer CODE_NOLOGIN=402;
	public  static  final  Integer CODE_NOAUTH=403;
	public  static  final  Integer CODE_EXCEPTION=500;
}
