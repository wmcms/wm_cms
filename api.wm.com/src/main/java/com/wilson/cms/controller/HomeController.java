package com.wilson.cms.controller;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.po.TUser;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.UMD5;
import com.wilson.cms.vo.LoginType;
import com.wilson.cms.vo.LoginVo;
import com.wilson.cms.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	 * @param type
	 * @param loginParam
	 * @return
	 */
	@PostMapping("/login/{loginway}")
	public Result Login(@PathVariable("loginway")LoginType type, @RequestBody LoginVo loginParam){

		return  null;
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
