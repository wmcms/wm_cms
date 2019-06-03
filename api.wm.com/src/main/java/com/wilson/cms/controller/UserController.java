package com.wilson.cms.controller;

import com.wilson.cms.config.Cms;
import com.wilson.cms.po.UserBehaviorPo;
import com.wilson.cms.po.UserInfoPo;
import com.wilson.cms.service.UserService;
import com.wilson.cms.vo.*;
import com.wilson.cms.vo.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

/**
 * @ClassName UserController
 * @Description 用户控制器
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

@RestController
@RequestMapping("/user")
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
    @GetMapping("/getlogininfo")
    public Result getLoginInfo(@RequestAttribute Long userId){
      return userService.getUser(userId);
    }
    /**
     *
     * @return
     */
//    @PostMapping("/password")
//    @ResponseBody
//    public AjaxResult Password(PasswordParam args) throws Exception {
//        TUser orgUser = userService.getById(args.getUserId());
//        if (StringUtils.test(args.getOrigPassword(),orgUser.getSlat(),orgUser.getPassword())){
//            TUser user = new TUser();
//            user.setSlat(cms.getSlat());
//            user.setId(args.getUserId());
//            user.setPassword(StringUtils.md5Encryption(args.getPassword(),user.getSlat()));
//            // userService.updateById(user);
//            return  AjaxResult.Success(null);
//
//        }
//        return  AjaxResult.Error("旧密码输入不正确");
//
//    }

    /**
     * 用户搜索
     * @param args
     * @return
     */
    @PostMapping("/search")
    public Result search(RequestParam args){
        PageResult<UserInfoPo> data = userService.search(args);
        Result result = Result.Success(data);
        return result;
    }

    /**
     * 用户行为搜索
     * @param args
     * @return
     */
    @PostMapping("/search/{behavior}")
    public Result search(@PathVariable BehaviorType behavior, RequestParam args){
        args.setBehaviorType(behavior);
        PageResult<UserBehaviorPo> data = userService.searchBehavior(args);
        Result result = Result.Success(data);
        return result;
    }
    @PostMapping("/save")
    public Result save(@RequestAttribute Long userId,UserInfoPo item) throws Exception {
        item.setCreateUserId(userId);
        item.setUpdateUserId(userId);
        return Result.Success(userService.save(item));
    }
    @PostMapping("/save/{behavior}")
    public Result save(@PathVariable BehaviorType behavior,@RequestAttribute Long userId,UserBehaviorPo item){
       item.setUserId(userId);
        item.setBehaviorType(behavior);
        return Result.Success(userService.saveBehavior(item));
    }






    @PostMapping("/batchdelete")
    public Result BatchDelete(@RequestBody ArrayList<Long> ids){
//        System.out.println(ids);
//        String[] strarray=ids.split(",");
//        List<Long> userIds = new ArrayList<Long>();
//        for (String item:strarray) {
//            userIds.add(Long.parseLong(item));
//        }
        System.out.println(ids);
        userService.batchDelete(ids);
        return Result.Success(null);
    }



//        if (user.getId()>0){ //保存
//            System.out.println(user);
//            if(user.getPassword()!=null){
//                user.setSlat(cms.getSlat());
//                user.setPassword(StringUtils.md5Encryption(user.getPassword(),user.getSlat()));
//            }
//            userService.save(user);
//            return  AjaxResult.Success(user.getId());
//        }
//        else{
//            //设置ID
//          //  user.setId(uID.NewID());
//            user.setSlat(cms.getSlat());
//            user.setPassword(StringUtils.md5Encryption(user.getPassword(),user.getSlat()));
//            userService.add(user);
//            return  AjaxResult.Success(user.getId());
//        }




}
