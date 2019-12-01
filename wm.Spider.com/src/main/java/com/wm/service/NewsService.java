package com.wm.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wm.mapper.INewsContentMapper;
import com.wm.mapper.INewsMapper;
import com.wm.po.NewsPo;
import com.wm.utils.StringUtils;

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
     * 保存文章
     * @param item
     * @return
     */
    @Transactional
    public  void save(NewsPo news) {        
        
            news.setId(StringUtils.newLongId(NewsPo.class));
            iNewsMapper.add(news);        
        if(news.getContent()!=null){
            Map<String,Object> map = new HashMap<>();
            map.put("id",news.getId());
            map.put("text",news.getContent());
            iNewsContentMapper.save(map);
        }

    }


}
