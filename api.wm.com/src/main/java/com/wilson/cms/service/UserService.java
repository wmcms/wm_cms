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
	TUserMapper tUserMapper;
	@Autowired
	private IUserMapper iUserMapper;



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
	 * @param page  分页参数
	 * @param username 用户名
	 * @return
	 */
	public PageResult<TUser> searchWithPageList(RequestArgs args){
		PageHelper.startPage(args.getPageIndex(),args.getPageSize());
		List<TUser> list= tUserMapper.searchUser(args);
		PageInfo<TUser> pageInfo = new PageInfo<TUser>(list);
		PageResult<TUser> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		System.out.println(result);
		pageInfo=null;
		System.out.println(result);
		return  result;
	}

	/**
	 * 批量删除数据
	 * @param userIds
	 */
	public  void  batchDelete(ArrayList<Long> userIds){
		System.out.println("service=======");
		System.out.println(userIds);
		tUserMapper.batchDelete(userIds);
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

	public TUser getById(Long userId) {
		return  tUserMapper.get(userId);
	}
	public void add(TUser item) {
		tUserMapper.add(item);
	}

	public Result exists(ExistsArgs args){
		TUser tUser = tUserMapper.queryExists(args.getID(), args.getKeyword());
		Map<String,Object> map = new HashMap<>() ;
		if(tUser!=null && (ExistsType.Mobile==args.getType()||ExistsType.UserName==args.getType())) {
			map.put("Name", args.getKeyword().equalsIgnoreCase(tUser.getName()));
			map.put("Mobile", args.getKeyword().equalsIgnoreCase(tUser.getMobile()));
		}
		else{
			map.put("Name", false);
			map.put("Mobile", false);
		}
		return Result.Success(map);

	}

	public	void updateById(TUser item){
		tUserMapper.update(item);
	}

}
