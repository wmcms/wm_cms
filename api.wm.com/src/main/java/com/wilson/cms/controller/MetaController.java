package com.wilson.cms.controller;

import com.github.pagehelper.PageInfo;
import com.wilson.cms.po.MetaPo;
import com.wilson.cms.vo.RequestParam;
import com.wilson.cms.service.MetaService;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName MetaController
 * @Description 数据字典控制器
 * @Author wilson
 * @Date 2019/5/27 22:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/meta")
public class MetaController
{
    @Autowired
    MetaService metaService;

    @PostMapping("/search")
    public Result search(RequestParam args) {
        return Result.Success(metaService.search(args)) ;
    }

    @PostMapping("/getall")
    public Result getAll(){
        return Result.Success(metaService.getAll());
    }


    /**
     *
     * @param item
     * @return
     */
    @PostMapping("/save")
    public  Result Save(MetaPo item) throws Exception {
        return  Result.Success(metaService.save(item));

    }

}
