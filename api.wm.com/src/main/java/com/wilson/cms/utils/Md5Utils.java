package com.wilson.cms.utils;

import org.springframework.util.DigestUtils;

import java.security.MessageDigest;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class Md5Utils {
	/**
	 * 工具类不允许被实例化
	 * @throws Exception
	 */
	private Md5Utils() throws Exception {
		throw new Exception("异常");
	}

	/**
	 * MD5方法
	 *
	 * @param text 明文
	 * @param slat 密钥
	 * @return 密文
	 * @throws Exception
	 */
	public static String Encryption(String text, String slat) throws Exception {
		if(!StringUtils.isEmpty(slat))
			text =text+"#"+slat;

		return Encryption(text);
	}
	public static String Encryption(String text) throws Exception {
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
	public static boolean Test(String text, String slat, String md5) throws Exception {
		if(!StringUtils.isEmpty(slat))
			text =text+"#"+slat;

		return Test(text,md5);
	}

	public static boolean Test(String text, String md5) throws Exception {
		//根据传入的密钥进行验证
		System.out.println("明文："+text);
		String md5Text = Encryption(text);
		System.out.println("密文："+md5Text);
		return  md5Text.equalsIgnoreCase(md5);
	}

}
