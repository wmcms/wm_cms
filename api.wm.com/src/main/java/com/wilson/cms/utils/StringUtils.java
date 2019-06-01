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
	
	/**
	 * 获取长度为 6 的随机数字
	 * @return 随机数字
	 * @date 修改日志：由 space 创建于 2018-8-2 下午2:43:51
	 */
	public static String newSmsCode() {
		char[] result = new char[UConstant.SMS_CODE_LENGTH];
		for (int index = 0; index < result.length; ++index) {
			result[index] = UConstant.NUMBER_STRING.charAt(random.nextInt(UConstant.NUMBER_STRING.length()));
		}
		return new String(result);
	}

	/**
	 * 获取长度为 6 的随机数字
	 * @return 随机数字
	 * @date 修改日志：由 space 创建于 2018-8-2 下午2:43:51
	 */
	public static String newImgCode() {
		char[] result = new char[UConstant.IMG_CODE_LENGTH];

		for (int index = 0; index < result.length; ++index) {
			result[index] = UConstant.CHAR_STRING.charAt(random.nextInt(UConstant.CHAR_STRING.length()));
		}
		return new String(result);
	}

	public  static  String newToken() throws Exception {
		return Md5Utils.Encryption(UUID.randomUUID().toString());
	}
}
