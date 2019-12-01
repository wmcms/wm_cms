package com.wilson.cms.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.po.NewsPo;
import com.wilson.cms.service.NewsService;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.ImageCodeVo;
import com.wilson.cms.vo.News;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;
import com.wilson.cms.vo.SmsCodeParam;
import com.zaxxer.hikari.util.SuspendResumeLock;


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
	@Autowired
	private UserService userService;

	@Autowired
	Cms cms;

	@Autowired
	NewsService newsService;

	// 超时时间三分钟
	private final int captchaTimeOut = 60 * 3;
	// @Autowired
	// private RedisTemplate<String, String> redisTemplate;
	@Autowired
	private DefaultKaptcha defaultKaptcha;

	/**
	 * 文章搜索
	 * 
	 * @param args
	 * @return
	 */
	@PostMapping("/news")
	public Result search(@RequestBody SearchParam args) {
		if (null == args.getPageIndex() || args.getPageIndex() <= 0) {
			args.setPageIndex(1);
		}
		if (null == args.getPageSize() || args.getPageSize() <= 0) {
			args.setPageSize(10);
		}
		args.setStatus(Short.valueOf("1"));// 前台查询是已布的
		PageResult<NewsPo> data = newsService.search(args);

		Result result = Result.Success(data);
		return result;
	}

	/**
	 * 获取文章详情
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("news/{id}")
	public Result Get(@PathVariable Long id) {
		News news = newsService.getById(id);

		if (news.getNews() != null) {
			newsService.addVisitCount(id);
		}

		return Result.Success(news);
	}

	/**
	 * 健康检查API
	 * 
	 * @return
	 */
	@GetMapping("/")
	public String Index() {
		return "ok";
	}

	@PostMapping("/check/account")
	public Result testAccount(@RequestParam String accountId) throws Exception {
		if(StringUtils.isEmpty(accountId)) {
			return Result.Error("帐号不能为空");
		}
		return userService.testAccount(accountId);
	}

	/**
	 * 获取图片验证码
	 * 
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/imagecode")
	public Result ImageCode() throws Exception {

		// 获取验证码字符串
		String text = StringUtils.newImgCode();
				//defaultKaptcha.createText();
		// 将用户名+时间戳生成唯一标识，验证码为值存储到redis
		String key = StringUtils.newToken();
		RedisUtils.set(key, text, 2 * 60);// TimeUnit.SECONDS
		// 生成图片
		BufferedImage bufferedImage = defaultKaptcha.createImage(text);
		ByteArrayOutputStream outputStream = null;
		try {
			outputStream = new ByteArrayOutputStream();
			ImageIO.write(bufferedImage, "jpg", outputStream);
		
			String base64= java.util.Base64.getEncoder() .encodeToString(outputStream.toByteArray());
		
			String captchaBase64 = "data:image/jpeg;base64," + base64.replaceAll("\r\n", "");
			ImageCodeVo imageCode = new ImageCodeVo();
			imageCode.setData(captchaBase64);
			imageCode.setId(key);
			return Result.Success(imageCode);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取短信验证码
	 * 
	 * @return
	 */
	@PostMapping("/sendsmscode")
	public Result SmsCode(@RequestBody SmsCodeParam param) {
		if(StringUtils.isEmpty(param.getMobile()))
			return Result.Error("请输入手机号");
		if(StringUtils.isMobile(param.getMobile())) 
			return Result.Error("请输入正确的手机号");	
		if(StringUtils.isEmpty(param.getImgCode())||StringUtils.isEmpty(param.getImgCodeId()))
			return Result.Error("请输入图片验证码");
		String imageCode= StringUtils.get(RedisUtils.get(param.getImgCodeId()));
		if(null==imageCode || !imageCode.equalsIgnoreCase(param.getImgCode()))		
			return Result.Error("验证码不正确或已过期");		
		String smsCode=StringUtils.newSmsCode();
		String key ="SMS_"+param.getMobile();
		RedisUtils.set(key, smsCode,5*60);
		
		return Result.Success("短信验证码是:"+ smsCode);
	}

}
