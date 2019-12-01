package com.wilson.cms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.cms.config.Cms;
import com.wilson.cms.po.UserBehaviorPo;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.RedisUtils;
import com.wilson.cms.vo.BehaviorType;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;
import com.wilson.cms.vo.UserBehaviorVo;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

@RestController
public class UserController
{
    @Autowired
    Cms cms;
    @Autowired
    private UserService userService;

    /**
     * 获取登录信息
     * @param userId
     * @return
     */
    @PostMapping("/user/updatestatus")
    public Result updateStatus(@RequestAttribute Long userId, @RequestBody UserPo userPo) {
        if (userPo.getId() > 0)
            return userService.updateStatus(userPo);
        else
            return Result.Error("参数错误");
    }

    /**
     * 获取登录信息
     * @param userId
     * @return
     */
    @GetMapping("/user/info")
    public Result getLoginInfo(@RequestAttribute Long userId){
        return userService.getUser(userId);
    }

    /**
     * 用户行为搜索
     * @param args
     * @return
     */
    @PostMapping("/my/{behavior}")
    public Result search(@PathVariable BehaviorType behavior, @RequestBody SearchParam args){
    	args.setDefaultValue();
        args.setBehaviorType(behavior);
        PageResult<UserBehaviorVo> data = userService.searchBehavior(args);
        Result result = Result.Success(data);
        return result;
    }



    /**
     * 注销
     * @return
     */
    @PostMapping("/user/logout")
    public Result Logout(@RequestAttribute String token){
        RedisUtils.del(token);
        return  Result.Success(null);
    }


    @PostMapping("/save/{behavior}")
    public Result save(@PathVariable BehaviorType behavior,@RequestAttribute Long userId,@RequestBody UserBehaviorPo item){
       item.setUserId(userId);
        item.setBehaviorType(behavior);
        return userService.saveBehavior(item);
    }

}
