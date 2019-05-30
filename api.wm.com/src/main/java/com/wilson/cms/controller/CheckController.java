package com.wilson.cms.controller;

import com.wilson.cms.service.UserService;
import com.wilson.cms.vo.ExistsArgs;
import com.wilson.cms.vo.ExistsType;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/check")
public class CheckController {
    @Autowired
    UserService userService;
    /**
     * 检查用户名是否可用
     * @param userId
     * @param userName
     * @return
     */
    @PostMapping("/username")
    @ResponseBody
    public Result UserName(Long id, String name){
        ExistsArgs args = new ExistsArgs();
        args.setID(id);
        args.setKeyword(name);
        args.setType(ExistsType.UserName);
        Result result = userService.exists(args);
        return result;
    }
    @PostMapping("/mobile")
    @ResponseBody
    public  Result Mobile(Long id,String mobile){
        ExistsArgs args = new ExistsArgs();
        args.setID(id);
        args.setKeyword(mobile);
        args.setType(ExistsType.UserName);
        Result result = userService.exists(args);
        return result;
    }


}
