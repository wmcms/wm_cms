package com.wilson.cms.controller;

import com.wilson.cms.config.Cms;
import com.wilson.cms.po.TUser;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.UID;
import com.wilson.cms.utils.UMD5;
import com.wilson.cms.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
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
    UID uID;

    @Autowired
    private UserService userService;

    /**
     * 登出API
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public Result Logout(String token) {
        userService.logout(token);
        return Result.Success(null);
    }


    /**
     * 用户搜索
     * @param args
     * @return
     */
    @PostMapping("/search")
    public Result Search(RequestArgs args){
        PageResult<TUser> data = userService.searchWithPageList(args);
        Result result = Result.Success(data);
        return result;
    }

    /**
     * 标签管理
     * @return
     */
    @GetMapping("")
    public  Result Index(){
        RequestArgs args = new RequestArgs();
        args.setPageIndex(1);
        args.setPageSize(10);
        PageResult<TUser> data = userService.searchWithPageList(args);
        Result result = Result.Success(data);
       return Result.Success(result);
    }



    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/edit")
    @ResponseBody
    public Result Edit(@RequestParam(value = "id",defaultValue = "0",required = false) Long id, ModelMap map){
        return  Result.Success(userService.getById(id));
    }
    /**
     *
     * @return
     */
    @GetMapping("/password")
    public String Password( ModelMap map){
        map.put("id",190023);
        return "user/password";
    }
    /**
     *
     * @param userId
     * @return
     */
    @PostMapping("/get")
    @ResponseBody
    public AjaxResult Get(Long userId){
        if(userId==0)
            return  AjaxResult.Success(new TUser());
      return AjaxResult.Success(userService.getById(userId));
    }

    @PostMapping("/batchdelete")
    @ResponseBody
    public AjaxResult BatchDelete(@RequestBody ArrayList<Long> ids){
//        System.out.println(ids);
//        String[] strarray=ids.split(",");
//        List<Long> userIds = new ArrayList<Long>();
//        for (String item:strarray) {
//            userIds.add(Long.parseLong(item));
//        }
        System.out.println(ids);
        userService.batchDelete(ids);
        return AjaxResult.Success(null);
    }
    /**

     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public  AjaxResult Save(TUser user) throws Exception {
        if (user.getId()>0){ //保存
            System.out.println(user);
            if(user.getPassword()!=null){
                user.setSlat(cms.getSlat());
                user.setPassword(UMD5.Encryption(user.getPassword(),user.getSlat()));
            }
            userService.updateById(user);
            return  AjaxResult.Success(user.getId());
        }
        else{
            //设置ID
            user.setId(uID.NewID());
            user.setSlat(cms.getSlat());
            user.setPassword(UMD5.Encryption(user.getPassword(),user.getSlat()));
            userService.add(user);
            return  AjaxResult.Success(user.getId());
        }

    }

    /**
     *
     * @return
     */
    @PostMapping("/password")
    @ResponseBody
    public AjaxResult Password(PasswordArgs args) throws Exception {
        TUser orgUser = userService.getById(args.getUserId());
        if (UMD5.Test(args.getOrigPassword(),orgUser.getSlat(),orgUser.getPassword())){
            TUser user = new TUser();
            user.setSlat(cms.getSlat());
            user.setId(args.getUserId());
            user.setPassword(UMD5.Encryption(args.getPassword(),user.getSlat()));
            userService.updateById(user);
            return  AjaxResult.Success(null);

        }
        return  AjaxResult.Error("旧密码输入不正确");

    }
}
