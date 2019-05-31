package com.wilson.cms.utils;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class StringUtils {
	/**
	 * * 判断一个字符串是否为空串
	 *
	 * @param str String
	 * @return true：为空 false：非空
	 */
	public static boolean isEmpty(String str)
	{
		return isNull(str) || UConstant.NULLSTR.equals(str.trim());
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
}
