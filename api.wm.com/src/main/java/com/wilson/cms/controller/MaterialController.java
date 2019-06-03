package com.wilson.cms.controller;

import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.service.MaterialService;
import com.wilson.cms.vo.RequestParam;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName MaterialController
 * @Description 素材管理控制器
 * @Author wilson
 * @Date 2019/5/29 20:55
 * @Version 1.0
 **/
@RestController
@RequestMapping("/material")
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @PostMapping("/search")
    public Result search(RequestParam args) {
        return Result.Success(materialService.search(args)) ;
    }

    /**
     *
     * @param item
     * @return
     */
    @PostMapping("/save")
    public  Result Save(@RequestAttribute Long userId, MaterialPo item) throws Exception {
        item.setCreateUserId(userId);
        return  materialService.save(item);
    }

    /**
     *
     * @param id
     * @return
     */
    @PostMapping("/del")
    public Result delete(Long id){
        return  materialService.removeAt(id.toString());
    }
}
