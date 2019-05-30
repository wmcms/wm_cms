package com.wilson.cms.controller;

import com.github.pagehelper.PageInfo;
import com.wilson.cms.po.TMeta;
import com.wilson.cms.vo.AjaxResult;
import com.wilson.cms.vo.Meta;
import com.wilson.cms.vo.RequestArgs;
import com.wilson.cms.service.MetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @ResponseBody
    public AjaxResult Search(RequestArgs args){
        PageInfo<Meta> data = metaService.searchWithPageList(args);
        AjaxResult result = AjaxResult.Success(data);
        return result;
    }

    @PostMapping("/get")
    @ResponseBody
    public AjaxResult Get(Long metaId){
        TMeta tCategory = new TMeta();
        if(metaId >0){
            tCategory= metaService.getById(metaId);
        }
        ModelMap map=new ModelMap();
        map.put("Item",tCategory);
        map.put("Items", metaService.getAllMeta());
        return AjaxResult.Success(map);
    }
    @PostMapping("/batchdelete")
    @ResponseBody
    public AjaxResult BatchDelete(String ids) {
        System.out.println(ids);
        String[] strarray = ids.split(",");
        List<Long> metaIds = new ArrayList<Long>();
        for (String item : strarray) {
            metaIds.add(Long.parseLong(item));
        }
        metaService.batchDelete(metaIds);


        return AjaxResult.Success(null);
    }

    /**
     *
     * @param item
     * @return
     */
    @PostMapping("/save")
    @ResponseBody
    public  AjaxResult Save(TMeta item) throws Exception {
        if (item.getMetaId()>0){ //保存
            metaService.updateById(item);
        }
        else{
          //  item.setMetaId(uID.NewID());
            metaService.add(item);
        }
        return  AjaxResult.Success(item.getMetaId());

    }

}
