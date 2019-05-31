package com.wilson.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.wilson.cms.config.Cms;
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
public class UserService implements IService<TUser>{

	@Autowired
	TUserMapper tUserMapper;
	@Autowired
	private IUserMapper iUserMapper;

	@Autowired
	URedis uRedis;

	@Autowired
	Cms cms;
	/**
	 * 用户登录
	 * @param args
	 * @return
	 */
	public Result login(LoginParam param) throws Exception {

		String loginKey=Md5Utils.Encryption(param.getLoginKey());
		TUser user = iUserMapper.queryExists(null,loginKey);
		boolean isVaild=param.getValidCode().equalsIgnoreCase(uRedis.get(param.getLoginKey()).toString());
		if(user==null){
			if(LoginMethod.password==param.getMethod())
					return Result.Error("用户不存在");
			if(!isVaild)
				return Result.Error("短信验证码不正确或已过期");
		}
		if(LoginMethod.password==param.getMethod()&&!isVaild)
			return Result.Error("短信验证码不正确或已过期");
		

		if (Md5Utils.Test(param.getPassword(),user.getSlat(),user.getPassword())){

			String token = cms.getToken().toUpperCase();
			uRedis.set(token,user);
			uRedis.expire(token,8*60*60);
			Map<String,Object> map = new HashMap<>() ;
			map.put("token",token);
			map.put("user",user);
			return Result.Success(map);
		}
		return Result.Error("用户名或密码不正确");
	}

	public boolean logout(String token) {
		uRedis.del(token);
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
	public  User getLoginUser(String token){
		User user = (User) uRedis.get(token);
		return user;
	}

	@Override
	public TUser getById(Long userId) {
		return  tUserMapper.get(userId);
	}
	@Override
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

	@Override
	public	void updateById(TUser item){
		tUserMapper.update(item);
	}

}
