package com.wilson.cms.controller;
import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.RedisUtils;
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
System.out.println(RedisUtils.get("a"));
		return new UserPo();
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

	/**
	 * 获取短信验证码
	 * @return
	 */
	@PostMapping("/logout")
	public Result Logout(@RequestAttribute("x-api-token")String token){

		RedisUtils.del(token);
		return  Result.Success(null);
	}
}
