package com.wilson.cms.utils;

import com.wilson.cms.vo.User;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
public class ULogin {

	/**
	 * 工具类不允许被实例化
	 * @throws Exception
	 */
	private ULogin() throws Exception {
		throw new Exception("异常");
	}

	/**
	 * 获取当前登录的用户信息
	 * @return
	 */
	public static User GetCurrentUser() {
		User user = new User();

		user.setUserId(1l);
		return user;
	}
}
