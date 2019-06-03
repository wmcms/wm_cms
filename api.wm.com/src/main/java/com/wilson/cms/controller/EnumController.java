package com.wilson.cms.controller;

import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.config.Cms;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.vo.DataType;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName EnumController
 * @Description 枚举控制器
 * @Author wilson
 * @Date 2019/5/29 21:48
 * @Version 1.0
 **/
@RestController
@AllowAnonymous
@RequestMapping("/enum")
public class EnumController {

    @Autowired
    Cms cms;
    @GetMapping("/{type}")
    public Result Index(@PathVariable("type") DataType type){
        switch (type){
            case usertype:
                return  Result.Success(cms.getUserType());
            case meateType:
                return  Result.Success(cms.getMeateType());
            case userstatus:
                return  Result.Success(cms.getUserStatus());
                default:
                    throw  new NotSupportExecption("不支持此类型");
        }
    }

}
