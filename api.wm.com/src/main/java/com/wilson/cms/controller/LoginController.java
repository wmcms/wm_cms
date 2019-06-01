package com.wilson.cms.controller;

import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.Constant;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.LoginMethod;
import com.wilson.cms.vo.LoginParam;
import com.wilson.cms.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	private static Logger logger=LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;

	/**
	 * 登录API
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public Result Login(@RequestBody LoginParam request) throws Exception {
		if(request.getMethod()==null)
			request.setMethod(LoginMethod.password);
		if(StringUtils.isEmpty(request.getLoginKey()))
			return  Result.Error("帐号不能为空");

		if (request.getMethod()==LoginMethod.password){
			if(StringUtils.isEmpty(request.getPassword()))
				return  Result.Error("登录密码不能为空");
		}
		else  {
			if(StringUtils.isEmpty(request.getSmsCode()))
				return  Result.Error("短信验证码不能为空");

			if(request.getMethod()!=LoginMethod.mobile){

				if(StringUtils.isEmpty(request.getOpenId()))
					return  Result.Error("openId 不能为空");
			}
		}

		return  userService.login(request);
	}

	/**
	 * 登录API
	 * @param request
	 * @return
	 */
	@PostMapping("/login/{method}")
	public Result Login(@PathVariable("method") LoginMethod method
			,@RequestBody LoginParam request) throws Exception {
		request.setMethod(method);
		switch (method){
			case mobile:
				return  mobileLogin(request);
			case weixin:
				return  weixinLogin(request);
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
	Result weixinLogin(LoginParam request) throws Exception {
		if(StringUtils.isEmpty(request.getOpenId()))
			return  Result.Error("openId不能为空");
		request.setLoginKey(request.getOpenId());
		return  userService.login(request);
	}
	/**
	 * 手机短信登录
	 * @param request
	 * @return
	 * @throws Exception
	 */
	Result mobileLogin(LoginParam request) throws Exception {
		if(StringUtils.isEmpty(request.getLoginKey()))
			return  Result.Error("手机号不能为空");

		if(StringUtils.isEmpty(request.getSmsCode()))
			return  Result.Error("短信验证码不能为空");

		return  userService.login(request);
	}

}
