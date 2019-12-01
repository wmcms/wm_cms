package com.wilson.cms.utils;

import com.wilson.cms.exception.NotSupportExecption;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * 字条串工具类
 */
public class StringUtils {

	public  StringUtils() {
		throw new NotSupportExecption(Constant.ERROR_UTIL_NOT_NEW);
	}
	/**
	 * 生机数生成器
	 */
	static Random random = new SecureRandom();
	/**
	 * * 判断一个字符串是否为空串
	 *
	 * @param str String
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(String str)
	{
		return isNull(str) || Constant.NULLSTR.equals(str.trim());
	}

	/**
	 * * 判断一个对象是否为空
	 *
	 * @param object Object
	 * @return true：为空 false：非空
	 */
	public static boolean isNull(Object object)
	{
		return object == null;
	}

	/**
	 * * 判断一个对象是否非空
	 *
	 * @param object Object
	 * @return true：非空 false：空
	 */
	public static boolean isNotNull(Object object)
	{
		return !isNull(object);
	}


	/**
	 * 获取长度为 6 的短信验证码
	 * @return
	 */
	public static String newSmsCode() {
		char[] result = new char[Constant.SMS_CODE_LENGTH];
		for (int index = 0; index < result.length; ++index) {
			result[index] = Constant.NUMBER_STRING.charAt(random.nextInt(Constant.NUMBER_STRING.length()));
		}
		return new String(result);
	}

	/**
	 * 获4位随机图片验证码
	 * @return
	 */

	public static String newImgCode() {
		char[] result = new char[Constant.IMG_CODE_LENGTH];

		for (int index = 0; index < result.length; ++index) {
			result[index] = Constant.CHAR_STRING.charAt(random.nextInt(Constant.CHAR_STRING.length()));
		}
		return new String(result);
	}

	/**
	 * 生成唯一的Token
	 * @return
	 * @throws Exception
	 */
	public  static  String newToken() throws Exception {
		return md5Encryption(UUID.randomUUID().toString());
	}


	/**
	 * MD5方法
	 *
	 * @param text 明文
	 * @param slat 密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String md5Encryption(String text, String slat) throws Exception {
		if(!StringUtils.isEmpty(slat))
			text =text+"#"+slat;

		System.out.println("mingwen:"+text);
		return md5Encryption(text);
	}
	public static String md5Encryption(String text) throws Exception {
		//加密后的字符串
		String md5Str = DigestUtils.md5DigestAsHex(text.getBytes("UTF8"));
		return md5Str.toUpperCase();
	}
	/**
	 * MD5验证方法
	 *
	 * @param text 明文
	 * @param md5 密文
	 * @return true/false
	 * @throws Exception
	 */
	public static boolean test(String text, String slat, String md5) throws Exception {
		if(!StringUtils.isEmpty(slat))
			text =text+"#"+slat;
		return test(text,md5);
	}

	public static boolean test(String text, String md5) throws Exception {
		String md5Text = md5Encryption(text);
		return  md5Text.equalsIgnoreCase(md5);
	}
	
	public static String get(Object val) {
		if(null==val) return "";
		return val.toString();
	}
	
	/*
	 * 是否是手机号
	 */
	public static boolean isMobile(String  mobile) {
		if(isEmpty(mobile)) return false;
		
		return mobile.matches(Constant.REGEX_MOBILE);
	}
	public static boolean isNickname(String  str) {
		if(isEmpty(str)) return true;
		
		return str.matches(Constant.REGEX_NICKNAME);
	}
	public static boolean isUserName(String  str) {
		if(isEmpty(str)) return false;
		
		return str.matches(Constant.REGEX_USERNAME);
	}
	/**
	   * 随机生成6位验证码
     * @return
     */
    public static String getRandomCode(Integer code){
        Random random = new Random();
        StringBuffer result= new StringBuffer();
        for (int i=0;i<code;i++){
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
    
    public static String getSmsCode(){
    	return getRandomCode(6);
    }
    
	/**
	 * 获取一个长整形的主键
	 * @param clizz 实体类
	 * @param <T> 类型
	 * @return
	 */
	public static  <T> Long newLongId(Class<T> clizz){
		Date date =new Date();
		String formatStr = new SimpleDateFormat(Constant.STR_ID_DATE_TIME_FORMAT).format(date);
		long resId = Long.parseLong(formatStr+"0000");
		String key =clizz.getName();
		return  resId+RedisUtils.incr(key);
	}

//	public static String getIp() {
//		HttpServletRequest request = getHttpServletRequest();
//		String ip = request.getHeader("X-Forwarded-For");
//		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("Proxy-Client-IP");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("WL-Proxy-Client-IP");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_CLIENT_IP");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getHeader("HTTP_X_FORWARDED_FOR");
//			}
//			if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
//				ip = request.getRemoteAddr();
//			}
//		}else if (ip != null && ip.length() > 15) {
//			String[] ips = ip.split(",");
//			for (int index = 0; index < ips.length; index++) {
//				String strIp = (String) ips[index];
//				if (!("unknown".equalsIgnoreCase(strIp))) {
//					ip = strIp;
//					break;
//				}
//			}
//		}
//		return ip;
//	}

}
