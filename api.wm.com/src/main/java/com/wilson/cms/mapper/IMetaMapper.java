package com.wilson.cms.mapper;

import com.wilson.cms.po.MetaPo;
import com.wilson.cms.vo.SearchParam;

import java.util.List;

public interface IMetaMapper {
    List<MetaPo> search(SearchParam args);
    void update(MetaPo item);
    void add(MetaPo item);
}