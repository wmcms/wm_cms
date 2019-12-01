package com.wilson.cms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wilson.cms.annotation.AllowAnonymous;
import com.wilson.cms.po.MetaPo;
import com.wilson.cms.service.MetaService;
import com.wilson.cms.utils.EnumUtils;
import com.wilson.cms.vo.DataType;
import com.wilson.cms.vo.ItemVo;
import com.wilson.cms.vo.Result;

/**
 * @ClassName MetaController
 * @Description 数据字典控制器
 * @Author wilson
 * @Date 2019/5/27 22:55
 * @Version 1.0
 **/
@RestController
@AllowAnonymous
@RequestMapping("/enum")
public class EnumController
{
    @Autowired
    MetaService metaService;


    @GetMapping("/{type}")
    public Result getEmuns(@PathVariable DataType type){
        List<MetaPo> all = metaService.getAll();
        @SuppressWarnings("rawtypes")
		List<ItemVo> items = new ArrayList<>();
        for (MetaPo item:all){
            if( item.getType()==type.getKey()){
                items.add(new ItemVo<Long>(item.getId(),item.getName()));
            }
        }
        return Result.Success(items);
    }

    @GetMapping("/list")
    public Result getEnum(){
        return Result.Success(EnumUtils.getEnums(DataType.class));
    }



}
