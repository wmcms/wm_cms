package com.wilson.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wilson.cms.config.Cms;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wilson.cms.utils.*;
import com.wilson.cms.mapper.*;
import com.wilson.cms.po.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * 系统用户
 * @ClassName: SysUserService
 * @author fuce
 * @date 2018年8月26日
 *
 */
@Service
public class UserService{

	@Autowired
	private IUserMapper iUserMapper;
	@Autowired
	private  IUserInfoMapper iUserInfoMapper;
	@Autowired
	private  IUserBehaviorMapper iUserBehaviorMapper;


	@Autowired
	Cms cms;
	/**
	 * 用户登录
	 * @param req
	 * @return
	 */
	public Result login(LoginParam req) throws Exception {
		String loginKey=StringUtils.md5Encryption(req.getLoginKey());
		UserPo user = iUserMapper.queryExists(null,loginKey);

			if(user==null)
		    return Result.Error("用户不存在");

		Result res = Result.Success(null);
		switch (req.getMethod()){
			case mobile:
				res=mobileLogin(req);
				break;
            case weixin:
                break;
			case password:
				res=passwordLogin(req,user);
				break;
				default:
					throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);
		}

		if (res.getCode()==Constant.CODE_SUCCESS){
			String token = StringUtils.newToken();
			RedisUtils.set(token,user);
			RedisUtils.expire(token,8*60*60);
			Map<String,Object> map = new HashMap<>() ;
			map.put("token",token);
			return Result.Success(map);
		}
		return res;
	}
	Result mobileLogin(LoginParam req){
		if(req.getSmsCode().equalsIgnoreCase(RedisUtils.get(req.getLoginKey()).toString()))
			return  Result.Success(null);
		return Result.Error("短信验证码不正确或已过期");
	}


	Result passwordLogin(LoginParam req,UserPo user) throws Exception {
		if(StringUtils.test(req.getPassword(),user.getSlat(),user.getPassword()))
			return  Result.Success(null);
		return Result.Error("用户名或密码不正确");
	}


	public boolean logout(String token) {
		RedisUtils.del(token);
		return  true;
	}
	/**
	 * 分页查询
	 * @param args  分页参数
	 * @return
	 */
	public PageResult<UserInfoPo> search(RequestArgs args){
		PageHelper.startPage(args.getPageIndex(),args.getPageSize());
		List<UserInfoPo> list= iUserInfoMapper.searchUser(args);
		PageInfo<UserInfoPo> pageInfo = new PageInfo<UserInfoPo>(list);
		PageResult<UserInfoPo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		System.out.println(result);
		pageInfo=null;
		System.out.println(result);
		return  result;
	}

	/**
	 * 用户行为查询
	 * @param args
	 * @return
	 */
	public  PageResult<UserBehaviorPo> searchBehavior(RequestArgs args){
		PageHelper.startPage(args.getPageIndex(),args.getPageSize());
		List<UserBehaviorPo> list= iUserBehaviorMapper.search(args);
		PageInfo<UserBehaviorPo> pageInfo = new PageInfo<UserBehaviorPo>(list);
		PageResult<UserBehaviorPo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		pageInfo=null;
		System.out.println(result);
		return  result;
	}

	/**
	 * 保存用户行为
	 * @param item
	 * @return
	 */
	public  Result saveBehavior(UserBehaviorPo item){
		if (item.getBehaviorType()==null) throw new NotSupportExecption(Constant.ERROR_NOT_SUPPORT_METHOD);

		switch (item.getBehaviorType()){
			case parise:
			case forward:
			case collection:
				item.setContent(null);
				item.setIp(null);
				break;
			case reply:
			case comment:
				item.setIp(null);
				break;
		}
		if (item.getId()==null){
			item.setId(StringUtils.newLoginId(UserBehaviorPo.class));
			iUserBehaviorMapper.add(item);
		}
		else{
			iUserBehaviorMapper.update(item);
		}
		return  Result.Success(item.getId());
	}

	/**
	 * 批量删除数据
	 * @param userIds
	 */
	public  void  batchDelete(ArrayList<Long> userIds){

	}


	/**
	 * 获取登录的用户信息
	 * @param token
	 * @return
	 */
	public  UserPo getLoginUser(String token){
		UserPo user = (UserPo) RedisUtils.get(token);
		return user;
	}

	public Result getUser(Long userId) {
		UserInfoPo user=  iUserInfoMapper.getUser(userId);
		if(user==null) return  Result.NoLogin("请先登录");

		UserVo vo = new UserVo();

		vo.setHeadUrl(user.getHeadUrl());
		vo.setName(user.getName());
		vo.setNickname(user.getNickname());
		List<String> rose = new ArrayList<>();
		if(user.getType()==1){
			rose.add("admin");
			vo.setRose(rose);
		}
		return  Result.Success(vo);
	}

	public Result existsMobile(String mobile){
		UserPo user = iUserMapper.queryExists(null, mobile);
		Map<String,Object> map = new HashMap<>() ;
		if(user!=null )
			map.put("mobile", true);
		else
			map.put("mobile", false);
		return Result.Success(map);

	}

	public	Result save(UserInfoPo item)
	{
		if(item.getId()==null){
			item.setId(StringUtils.newLoginId(UserPo.class));
			//iUserInfoMapper.add();
		}
		return Result.Success(item.getId());
	}

}
