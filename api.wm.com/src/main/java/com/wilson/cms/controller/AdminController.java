package com.wilson.cms.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.cms.config.Cms;
import com.wilson.cms.exception.NotSupportExecption;
import com.wilson.cms.po.AdPo;
import com.wilson.cms.po.MetaPo;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.service.AdService;
import com.wilson.cms.service.MaterialService;
import com.wilson.cms.service.MetaService;
import com.wilson.cms.service.NewsService;
import com.wilson.cms.service.UserService;
import com.wilson.cms.utils.FileUtils;
import com.wilson.cms.vo.MaterialVo;
import com.wilson.cms.vo.News;
import com.wilson.cms.vo.RemoveFileParam;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;
import com.wilson.cms.vo.TableType;

/** 
 * @ClassName AdminController
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/29 19:18
 * @Version 1.0
 **/
@RestController
public class AdminController {

    @Autowired
    MetaService metaService;
    @Autowired
    NewsService newsService;
    @Autowired
    UserService userService;
    @Autowired
    MaterialService materialService;
    @Autowired
    AdService adService;
    @Autowired
    Cms cms;

    /**
     * 列表分页查询
     * @param table
     * @param args
     * @return
     */
    @PostMapping("/search/{table}")
    public Result search(@PathVariable TableType table, @RequestBody SearchParam args) {
        switch (table) {
            case meta:
                return Result.Success(metaService.search(args));
            case news:
                return  Result.Success(newsService.search(args));
            case user:
                return Result.Success(userService.search(args));
            case material:
            return  Result.Success(materialService.search(args));
            case ad:
                return  Result.Success(adService.search(args));
            default:
                throw new NotSupportExecption(table.name());
        }

    }
    @GetMapping("get/{table}/{id}")
    public Result Get(@PathVariable TableType table,@PathVariable Long id){
        switch (table) {
            case news:
                return Result.Success(newsService.getById(id));
            default:
                throw new NotSupportExecption(table.name());
        }
    }
    /**
     *  移除数据
     * @param table
     * @param idList
     * @return
     */
    @PostMapping("/remove/{table}")
    public Result remove(@RequestAttribute Long userId, @PathVariable TableType table, @RequestBody List<Long> idList){
        switch (table) {
            case meta:
                return metaService.remove(userId,idList);
            case user:
                return userService.remove(userId,idList);
            case news:

            case material:
            default:
                throw new NotSupportExecption(table.name());
        }
    }
    /**
     *
     * @param item
     * @return
     */
    @PostMapping("/save/material")
    public  Result Save(@RequestAttribute Long userId, @RequestBody MaterialVo item) throws Exception {
        item.getMaterialPo().setCreateUserId(userId);
        return  materialService.save(item);
    }


    @PostMapping("/remove/files")
    public Result removeAt(@RequestBody List<RemoveFileParam> params){

        for (RemoveFileParam info :params){
            if(info.getId()>0){
            }
            else{
                FileUtils.Config(cms);
                FileUtils.RemoveAt(info.getUrl());
            }
        }

        return Result.Success(null);
    }

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
    }

    /**
     * 保存广告
     * @param userId
     * @param item
     * @return
     */
    @PostMapping("/save/ad")
    public Result Save(@RequestAttribute Long userId, @RequestBody AdPo item){
        item.setCreateUserId(userId);
        item.setUpdateUserId(userId);
        return  adService.save(item);
    }
    @PostMapping("/save/meta")
    public Result save(@RequestAttribute Long userId, @RequestBody MetaPo item) {
        item.setUpdateUserId(userId);
        item.setCreateUserId(userId);
        return metaService.save(item);
    }

    /**
     * 保存用户信息
     * @param userId
     * @param item
     * @return
     * @throws Exception
     */
    @PostMapping("save/user")
    public Result save(@RequestAttribute Long userId,@RequestBody UserPo item) throws Exception {
        item.setCreateUserId(userId);
        item.setUpdateUserId(userId);
        return Result.Success(userService.save(item));
    }
    @PostMapping("/save/news")
    public Result save(@RequestAttribute Long userId, @RequestBody News item){
        item.getNews().setCreateUserId(userId);
        return newsService.save(item);
    }
}
