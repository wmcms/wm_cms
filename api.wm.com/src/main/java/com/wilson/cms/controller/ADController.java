package com.wilson.cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.cms.po.AdPo;
import com.wilson.cms.service.AdService;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;

/**
 * @ClassName ADController
 * @Description 广告管理控制器
 * @Author wilson
 * @Date 2019/5/29 20:52
 * @Version 1.0
 **/
@RestController
@RequestMapping("/ad")
public class ADController {

    @Autowired
    AdService adService;

    /**
     * 查询广告列表
     * @param args
     * @return
     */
    @PostMapping("/search")
    public Result Search(SearchParam args){
        PageResult<AdPo> data = adService.search(args);
        Result result = Result.Success(data);
        return result;
    }
    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    @PostMapping("/save")
    public Result Save(@RequestAttribute Long userId, AdPo item){
        item.setCreateUserId(userId);
        item.setUpdateUserId(userId);
        return  adService.save(item);
    }

}
