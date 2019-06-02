package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.IMaterialMapper;
import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.po.NewsPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.RequestArgs;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName MaterialService
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 21:03
 * @Version 1.0
 **/
@Service
public class MaterialService {

    @Autowired
    IMaterialMapper iMaterialMapper;

    /**
     * 搜索素材
     * @param args
     * @return
     */
    public PageResult<MaterialPo> search(RequestArgs args){
        PageHelper.startPage(args.getPageIndex(),args.getPageSize());
        List<MaterialPo> list= iMaterialMapper.search(args);
        PageInfo<MaterialPo> pageInfo = new PageInfo<MaterialPo>(list);
        PageResult<MaterialPo> result = new PageResult<>();
        result.setItems(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        pageInfo=null;
        return  result;
    }

    /**
     * 保存素材
     * @param item
     * @return
     */
    public Result Save(MaterialPo item){
        item.setId(StringUtils.newLoginId(MaterialPo.class));
        iMaterialMapper.add(item);
        return  Result.Success(item.getId());
    }

    /**
     * 删除素材
     * @param ids
     * @return
     */
    public Result removeAt(String ids){
        iMaterialMapper.removeAt(ids);
        return  Result.Success(ids);
    }
}
