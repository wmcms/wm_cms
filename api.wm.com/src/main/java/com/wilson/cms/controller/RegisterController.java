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
import com.wilson.cms.vo.RegisterParam;
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
public class RegisterController {
	@Autowired
	private UserService userService;

	/**
	 * 注册API
	 * @param request
	 * @return
	 */
	@PostMapping("/register")
	public Result Login( @RequestBody RegisterParam request) throws Exception {
		if(StringUtils.isEmpty(request.getAccountId()))
			return  Result.Error("帐号不能为空");
		if(!StringUtils.isUserName(request.getAccountId()))
			return  Result.Error("帐号只能是包含字母、数字、下划线、空格及. @字符的6-20位字符串");
		if(StringUtils.isEmpty(request.getPassword()))
			return  Result.Error("密码不能为空");
		if(!StringUtils.isEmpty(request.getNickname())) {
			//验证格式
			if(!StringUtils.isNickname(request.getNickname()))
				return  Result.Error("昵称只能是包含中文字符、字母、数字、下划线、空格的2-20位字符串");
		}
		
		if(StringUtils.isEmpty(request.getImgCode())||StringUtils.isEmpty(request.getImgCodeId()))
			return Result.Error("请输入图片验证码");
		String imageCode= StringUtils.get(RedisUtils.get(request.getImgCodeId()));
		if(null==imageCode || !imageCode.equalsIgnoreCase(request.getImgCode()))		
			return Result.Error("验证码不正确或已过期");		
		
		RedisUtils.del(request.getImgCodeId());
		return  userService.register(request);
	}

	
}
