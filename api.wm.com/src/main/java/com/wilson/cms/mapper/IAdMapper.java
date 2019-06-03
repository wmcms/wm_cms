package com.wilson.cms.mapper;

import com.wilson.cms.po.AdPo;
import com.wilson.cms.vo.RequestParam;

import java.util.List;

/**
 *
 */

public interface IAdMapper {

   public List<AdPo> search(RequestParam args);

   void update(AdPo item);

   void add(AdPo user);
}