package com.wilson.cms.utils;

import java.security.MessageDigest;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class Constant {

	public  static final String DATE_FORMAT="yyyy-MM-dd";
	public  static final String DATE_TIME_FORMAT="yyyy-MM-dd HH:mm:ss";
	public  static final String STR_DATE_TIME_FORMAT="yyyyMMddHHmmss";
	public  static final String STR_ID_DATE_TIME_FORMAT="yyMMddHHmmss";
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
		/**
	 * 短信验证码长度
	 */
	public  static  final  Integer SMS_CODE_LENGTH=6;

	/**
	 * 图片验证码长度
	 */
	public  static  final  Integer IMG_CODE_LENGTH=4;

	public static final String NUMBER_STRING = "0123456789"; // 数字
	public static final String CHAR_STRING = "ABCKL0123456789MNOPQRSTghiklUVXYZ0123456789abcdefmn0123456789opqrstDEFGHIuvxyx";

	public static  final String ERROR_UTIL_NOT_NEW="工具类不支持实例化";
	public static  final String ERROR_NOT_SUPPORT_METHOD="不支持此操作";
}
