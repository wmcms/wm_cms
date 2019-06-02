package com.wilson.cms.mapper;

import com.wilson.cms.po.NewsPo;
import com.wilson.cms.vo.RequestArgs;

import java.util.List;

public interface INewsMapper {

    void add(NewsPo item);
    void update(NewsPo item);
    List<NewsPo> search(RequestArgs args);
    NewsPo get(Long id);
}