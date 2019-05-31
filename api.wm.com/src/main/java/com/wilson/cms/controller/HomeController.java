package com.wilson.cms.controller;
import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.*;
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
public class HomeController {
	private static Logger logger=LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private UserService userService;

	@Autowired
	Cms cms;
	@GetMapping("/test")
	public  Object Test(){

		return cms.getUserType();
	}

	/**
	 * 健康检查API
	 * @return
	 */
	@GetMapping("/")
	public String Index() {
		return "ok";
	}

	/**
	 * 登录API
	 * @param request
	 * @return
	 */
	@PostMapping("/login")
	public Object Login(@RequestBody LoginParam request) throws Exception {
		if(request.getMethod()==null)
			request.setMethod(LoginMethod.password);
		if(StringUtils.isEmpty(request.getLoginKey()))
			return  Result.Error("帐号不能为空");

		if (request.getMethod()==LoginMethod.password){
			if(StringUtils.isEmpty(request.getPassword()))
				return  Result.Error("登录密码不能为空");
		}
		else  {
			if(StringUtils.isEmpty(request.getValidCode()))
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
	public Object Login(@PathVariable("method") LoginMethod method,@RequestBody LoginParam request){

		return  request;
	}

	/**
	 * 获取图片验证码
	 * @return
	 */
	@GetMapping("/imagecode")
	public Result ImageCode(){
		return  null;
	}

	/**
	 * 获取短信验证码
	 * @return
	 */
	@PostMapping("/smscode")
	public Result SmsCode(){
		return  null;
	}
}
