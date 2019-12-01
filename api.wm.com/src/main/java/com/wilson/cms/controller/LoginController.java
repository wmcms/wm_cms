package com.wilson.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.Constant;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.LoginMethod;
import com.wilson.cms.vo.LoginParam;
import com.wilson.cms.vo.Result;

/**
 * @ClassName HomeController
 * @Description 根控制器，免登录接口
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

@RestController
@AllowAnonymous
public class LoginController {
	@Autowired
	private UserService userService;

	/**
	 * 登录API
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public Result Login( @RequestBody LoginParam request) throws Exception {
		if(null==request.getMethod()) {
			request.setMethod(LoginMethod.password);
		}
		switch (request.getMethod()) {
		case mobile:
			return  mobileLogin(request);
		case weixin:
			return  weixinLogin(request);
		case password:
			return  passwordLogin(request);	
		default:
			throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);
		}

	}

	/**
	 * 微信认证登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Result weixinLogin(@RequestBody LoginParam request) throws Exception {
		if(StringUtils.isEmpty(request.getAccountId()))
			return  Result.Error("openId不能为空");
		//request.setLoginKey(request.getUnionId());
		return  userService.login(request);
	}
	/**
	 * 手机短信登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Result mobileLogin(@RequestBody LoginParam request) throws Exception {
		if(StringUtils.isEmpty(request.getAccountId()))
			return  Result.Error("手机号不能为空");
		if(!StringUtils.isMobile(request.getAccountId()))
			return  Result.Error("请输入正确的手机号码");
		if(StringUtils.isEmpty(request.getSmsCode()))
			return  Result.Error("短信验证码不能为空");
		
		String key ="SMS_"+request.getAccountId();
		String smsCode =StringUtils.get(RedisUtils.get(key));
		if(!request.getSmsCode().equalsIgnoreCase(smsCode)) {
			//String errorKey="SMS_ERR_"+request.getAccountId();
			//TODO为安全考虑，防止暴力破解验证码，应该加一些限制
			return Result.Error("短信验证码不正确或已过期");
		}
		

		return  userService.login(request);
	}

	/**
	 * 帐号密码信登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private Result passwordLogin(LoginParam request) throws Exception {

		if(StringUtils.isEmpty(request.getAccountId()))
			return  Result.Error("帐号不能为空");

			if(StringUtils.isEmpty(request.getPassword()))
				return  Result.Error("登录密码不能为空");

		return  userService.login(request);
	}
}
