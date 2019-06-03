package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.INewsContentMapper;
import com.wilson.cms.mapper.INewsMapper;
import com.wilson.cms.po.NewsPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.RequestParam;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName NewsService
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 0:30
 * @Version 1.0
 **/
@Service
public class NewsService {

    @Autowired
    INewsContentMapper iNewsContentMapper;
    @Autowired
    INewsMapper iNewsMapper;

    /**
     * 文章列表查询
     * @param args
     * @return
     */
    public PageResult<NewsPo> search(RequestParam args){
        PageHelper.startPage(args.getPageIndex(),args.getPageSize());
        List<NewsPo> list= iNewsMapper.search(args);
        PageInfo<NewsPo> pageInfo = new PageInfo<NewsPo>(list);
        PageResult<NewsPo> result = new PageResult<>();
        result.setItems(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        pageInfo=null;
        return  result;
    }

    /**
     * 获取文章
     * @param id
     * @return
     */
    public Result getById(Long id){
        return Result.Success(iNewsMapper.get(id));
    }

    /**
     * 保存文章
     * @param item
     * @return
     */
    @Transactional
    public  Result Save(NewsPo item) {
        if (item.getId() == null) {
            item.setId(StringUtils.newLoginId(NewsPo.class));
            iNewsMapper.add(item);
            Map<String, Object> map = new HashMap<>();
            map.put("id", item.getId());
            map.put("text", item.getContent());
            iNewsContentMapper.save(map);
        }
        else{
            iNewsMapper.update(item);
        }
        return  Result.Success(item.getId());
    }
}
