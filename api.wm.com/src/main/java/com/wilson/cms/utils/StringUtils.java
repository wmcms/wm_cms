package com.wilson.cms.utils;

import com.wilson.cms.exception.NotSupportExecption;
import org.springframework.util.DigestUtils;
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

	/**
	 * 获取一个长整形的主键
	 * @param clizz 实体类
	 * @param <T> 类型
	 * @return
	 */
	public static  <T> Long newLoginId(Class<T> clizz){
		Date date =new Date();
		String formatStr = new SimpleDateFormat(Constant.STR_ID_DATE_TIME_FORMAT).format(date);
		long resId = Long.parseLong(formatStr+"0000");
		String key =clizz.getName();
		return  resId+RedisUtils.incr(key);
	}
}
