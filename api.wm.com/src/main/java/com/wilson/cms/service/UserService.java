package com.wilson.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.config.Cms;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.mapper.INewsReportMapper;
import com.wilson.cms.mapper.IUserAccountMapper;
import com.wilson.cms.mapper.IUserBehaviorMapper;
import com.wilson.cms.mapper.IUserMapper;
import com.wilson.cms.po.UserAccountPo;
import com.wilson.cms.po.UserBehaviorPo;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.utils.Constant;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.LoginParam;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.RegisterParam;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;
import com.wilson.cms.vo.UserBehaviorVo;
import com.wilson.cms.vo.UserVo;

/**
 * 系统用户
 * 
 * @ClassName: SysUserService
 * @author fuce
 * @date 2018年8月26日
 *
 */
@Service
public class UserService {

	@Autowired
	private IUserAccountMapper iUserAccountMapper;
	@Autowired
	private IUserMapper iUserMapper;
	@Autowired
	private IUserBehaviorMapper iUserBehaviorMapper;

	@Autowired
	private INewsReportMapper iNewsReportMapper;

	@Autowired
	Cms cms;

	/**
	 * 用户登录
	 * 
	 * @param req
	 * @return
	 */
	public Result login(LoginParam req) throws Exception {
		String accountId = StringUtils.md5Encryption(req.getAccountId());
		UserAccountPo account = iUserAccountMapper.getById(accountId);

		Result res = Result.Success(null);
		switch (req.getMethod()) {
		case mobile:
			res = mobileLogin(req, account);
			break;
		case weixin:
			if (account == null)
				return Result.Error("用户不存在");
			break;
		case password:
			res = passwordLogin(req, account);
			break;
		default:
			throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);
		}

		if (res.getCode() == Constant.CODE_SUCCESS) {
			return createToken(account);
		}
		return res;
	}
	
	/**
	 * 
	 * @param req
	 * @return
	 * @throws Exception
	 */
	public Result register(RegisterParam req) throws Exception {
		String accountId = StringUtils.md5Encryption(req.getAccountId());
		UserAccountPo account = iUserAccountMapper.getById(accountId);
		if (account == null) {

			// step 1 创建用户
			UserPo user = new UserPo();
			user.setId(StringUtils.newLongId(UserPo.class));			
			user.setName(req.getNickname());
			user.setNickname(req.getNickname());
			user.setRole(2);
			iUserMapper.add(user);

			// step 2 注册帐号
			account = new UserAccountPo();
			account.setId(accountId);
			account.setUserId(user.getId());
			account.setAuthMethod(2);
			account.setType(2);
			account.setPassword(StringUtils.md5Encryption(req.getPassword(), user.getId().toString()));
			iUserAccountMapper.register(account);

			return Result.Success(null);
		}
		return Result.Error("帐号已被注册");

	}

	/**
	 * 创建登录令牌
	 * 
	 * @param account
	 * @return
	 * @throws Exception
	 */
	Result createToken(UserAccountPo account) throws Exception {
		String token = StringUtils.newToken();
		RedisUtils.set(token, account);
		RedisUtils.expire(token, 8 * 60 * 60);
		Map<String, Object> map = new HashMap<>();
		map.put("token", token);
		return Result.Success(map);
	}

	/**
	 * 手机登录
	 * 
	 * @param req
	 * @param account
	 * @return
	 * @throws Exception
	 */
	Result mobileLogin(LoginParam req, UserAccountPo account) throws Exception {

		if (account == null) {
			// 要注册
			// step 1 创建用户
			UserPo user = new UserPo();
			user.setId(StringUtils.newLongId(UserPo.class));
			String name = "WM_" + StringUtils.newSmsCode();
			user.setName(name);
			user.setNickname(name);
			user.setRole(2);
			iUserMapper.add(user);

			// step 2 注册帐号
			account = new UserAccountPo();
			account.setId(StringUtils.md5Encryption(req.getAccountId()));
			account.setUserId(user.getId());
			account.setAuthMethod(1);
			account.setType(1);
			iUserAccountMapper.register(account);
		}
		if ((account.getAuthMethod() & 1) == 1)
			return Result.Success(null);
		else
			return Result.Error(Constant.ERROR_NOT_SUPPORT_METHOD);
	}

	/**
	 * 密码认证登录
	 * 
	 * @param req
	 * @param account
	 * @return
	 * @throws Exception
	 */
	Result passwordLogin(LoginParam req, UserAccountPo account) throws Exception {
		if (account == null || StringUtils.isEmpty(account.getPassword()) || (account.getAuthMethod() & 2) != 2
				|| !StringUtils.test(req.getPassword(), account.getUserId().toString(), account.getPassword()))
			return Result.Error("用户名或密码不正确");

		return Result.Success(null);

	}

	/**
	 * 用户查询
	 * 
	 * @param args 分页参数
	 * @return
	 */
	public PageResult<UserPo> search(SearchParam args) {
		PageHelper.startPage(args.getPageIndex(), args.getPageSize());
		List<UserPo> list = iUserMapper.searchUser(args);
		PageInfo<UserPo> pageInfo = new PageInfo<UserPo>(list);
		PageResult<UserPo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		pageInfo = null;
		return result;
	}

	/**
	 * 用户行为查询
	 * 
	 * @param args
	 * @return
	 */
	public PageResult<UserBehaviorVo> searchBehavior(SearchParam args) {
		PageHelper.startPage(args.getPageIndex(), args.getPageSize());
		List<UserBehaviorVo> list = iUserBehaviorMapper.search(args);
		PageInfo<UserBehaviorVo> pageInfo = new PageInfo<UserBehaviorVo>(list);
		PageResult<UserBehaviorVo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		pageInfo = null;
		return result;
	}

	/**
	 * 保存用户行为
	 * 
	 * @param item
	 * @return
	 */
	@Transactional
	public Result saveBehavior(UserBehaviorPo item) {
		if (item.getBehaviorType() == null)
			throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);

		if (StringUtils.isEmpty(item.getTitle()))
			return Result.Error("项目标题不能为空");

		switch (item.getBehaviorType()) {
		case parise:
		case forward:
		case collection:
			item.setContent(null);
			item.setIp(null);
			break;
		case reply:
		case comment:
			item.setIp(null);
			if (StringUtils.isEmpty(item.getContent()))
				return Result.Error("内容不能为空");
			break;
		case vote:
			break;
		default:
			throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);
		}
		if (item.getId() == null) {
			item.setId(StringUtils.newLongId(UserBehaviorPo.class));
			iUserBehaviorMapper.add(item);
		} else {
			iUserBehaviorMapper.update(item);
		}
		switch (item.getBehaviorType()) {
		case parise:
		case forward:
		case collection:
		case comment:
			Map<String, Object> map = new HashMap<>();
			map.put("id", item.getId());
			map.put("key", item.getBehaviorType() + "_count");
			iNewsReportMapper.save(map);
			break;
		default:
			break;
		}

		return Result.Success(item.getId());
	}

	/**
	 * 获取登录用户信息用于权限认证
	 * 
	 * @param userId
	 * @return
	 */
	public Result getUser(Long userId) {
		System.out.print(userId);
		UserPo user = iUserMapper.getUser(userId);
		if (user == null)
			return Result.NoLogin("请先登录");

		UserVo vo = new UserVo();

		vo.setHeadUrl(user.getHeadUrl());
		vo.setName(user.getName());
		vo.setNickname(user.getNickname());
		List<String> rose = new ArrayList<>();
		if (user.getRole() == 1) {
			rose.add("admin");
			vo.setRose(rose);
		}
		return Result.Success(vo);
	}

	/**
	 * 批量删除数据
	 * 
	 * @param userIds
	 */
	public void batchDelete(ArrayList<Long> userIds) {

	}

	/**
	 * 检查手机号是否重复
	 * 
	 * @param mobile
	 * @return
	 * @throws Exception
	 */
	public Result existsMobile(String mobile) throws Exception {
		String id = StringUtils.md5Encryption(mobile);
		UserAccountPo user = iUserAccountMapper.getById(id);
		Map<String, Object> map = new HashMap<>();
		if (user != null)
			map.put("mobile", true);
		else
			map.put("mobile", false);
		return Result.Success(map);

	}

	public Result testAccount(String accountId) throws Exception {
		UserAccountPo account = iUserAccountMapper.getById(StringUtils.md5Encryption(accountId));
		if (account == null)
			return Result.Success(null);
		else
			return Result.Error("已被注册");

	}

	/**
	 * 保存用户信息
	 * 
	 * @param item
	 * @return
	 */
	@Transactional
	public Result save(UserPo item) throws Exception {
		if (StringUtils.isEmpty(item.getMobile()))
			return Result.Error("手机号不能为空");
		if (!StringUtils.isMobile(item.getMobile()))
			return Result.Error("请输入正确的手机号");
		UserAccountPo account = new UserAccountPo();
		if (item.getId() == null || item.getId() <= 0) {
			// step 1 保存用户信息
			item.setId(StringUtils.newLongId(UserAccountPo.class));
			if (StringUtils.isEmpty(item.getNickname()))
				item.setNickname("WM_" + item.getMobile());
			iUserMapper.add(item);

			// step 2 注册帐号
			if (StringUtils.isEmpty(item.getPassword()))
				return Result.Error("密码不能空");
			account.setUserId(item.getId());
			account.setId(StringUtils.md5Encryption(item.getMobile()));
			account.setType(1);
			account.setAuthMethod(3);
			account.setPassword(StringUtils.md5Encryption(item.getPassword(), item.getId().toString()));
			iUserAccountMapper.register(account);

		} else {
//			user.setId(item.getId());
//			user.setLoginKey(StringUtils.md5Encryption(item.getMobile()));
//			if (false == StringUtils.isEmpty(item.getPassword())) {
//				user.setSlat(StringUtils.newImgCode());
//				user.setPassword(StringUtils.md5Encryption(item.getPassword(), user.getSlat()));
//			}
//			iUserMapper.update(user);
			iUserMapper.update(item);
		}
		return Result.Success(item.getId());
	}

	public Result remove(Long userId, List<Long> idList) {

		// UserAccountPo userPo = null;
//		for (Long id : idList) {
//			userPo = new UserAccountPo();
//			userPo.setId(id);
//			userPo.setStatus(-1);
//			iUserMapper.update(userPo);
//		}
		iUserMapper.batchDelete(idList);
		return Result.Success(idList.size());
	}

	/**
	 * 修改状态
	 * 
	 * @param item
	 * @return
	 */
	public Result updateStatus(UserPo item) {
		iUserMapper.update(item);
		return Result.Success(item);
	}
}
