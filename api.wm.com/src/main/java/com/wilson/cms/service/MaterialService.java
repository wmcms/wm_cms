package com.wilson.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.IMaterialMapper;
import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.FileVo;
import com.wilson.cms.vo.MaterialVo;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;

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
    public PageResult<MaterialPo> search(SearchParam args){
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
    public Result save(MaterialVo item){

      MaterialPo materialPo = item.getMaterialPo();
      if(materialPo.getId()>0){
      }
      else {
          for (FileVo file : item.getFiles()){
              try {
                  materialPo.setFileName(file.getName());
                  materialPo.setFileType(file.getType());
                  materialPo.setFileSize(file.getSize());
                  materialPo.setUrl(file.getUrl());
                  materialPo.setId(StringUtils.newLongId(MaterialPo.class));
                  iMaterialMapper.add(materialPo);
              }
              catch (Exception ex){
                  continue;
              }
          }

      }
      return  Result.Success(materialPo.getId());
    }

    /**
     * 删除素材
     * @param ids
     * @return
     */
    public Result removeAt(Long id){
        iMaterialMapper.removeAt(id);
        return  Result.Success(id);
    }
}
