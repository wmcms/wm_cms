package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.IAdMapper;
import com.wilson.cms.po.AdPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.RequestParam;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName AdService
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 0:30
 * @Version 1.0
 **/
@Component
public class AdService {

    @Autowired
    IAdMapper iAdMapper;

    public PageResult<AdPo> searchWithPageList(RequestParam args){
        PageHelper.startPage(args.getPageIndex(),args.getPageSize());
        List<AdPo> list= iAdMapper.search(args);
        PageInfo<AdPo> pageInfo = new PageInfo<AdPo>(list);
        PageResult<AdPo> result = new PageResult<>();
        result.setItems(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        System.out.println(result);
        pageInfo=null;
        System.out.println(result);
        return  result;
    }


    public Result save(AdPo item){

        if(item.getId()==null){
            item.setId(StringUtils.newLoginId(AdPo.class));
            iAdMapper.add(item);
        }
        else{
            iAdMapper.update(item);
        }
        return  Result.Success(item.getId());
    }
}
