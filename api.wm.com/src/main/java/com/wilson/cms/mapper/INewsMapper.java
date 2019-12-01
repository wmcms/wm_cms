package com.wilson.cms.mapper;

import java.util.List;

import com.wilson.cms.po.NewsPo;
import com.wilson.cms.vo.SearchParam;

public interface INewsMapper {

    void add(NewsPo item);
    void update(NewsPo item);
    List<NewsPo> search(SearchParam args);
    NewsPo get(Long id);
}