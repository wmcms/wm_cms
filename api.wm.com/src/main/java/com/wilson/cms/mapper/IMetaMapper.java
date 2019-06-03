package com.wilson.cms.mapper;

import com.wilson.cms.po.MetaPo;
import com.wilson.cms.vo.RequestParam;

import java.util.List;

public interface IMetaMapper {
    List<MetaPo> search(RequestParam args);
    void update(MetaPo item);
    void add(MetaPo item);
}