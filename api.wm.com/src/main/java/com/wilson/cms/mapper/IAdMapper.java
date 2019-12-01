package com.wilson.cms.mapper;

import com.wilson.cms.po.AdPo;
import com.wilson.cms.vo.SearchParam;

import java.util.List;

/**
 *
 */

public interface IAdMapper {

   public List<AdPo> search(SearchParam args);

   void update(AdPo item);

   void add(AdPo user);
}