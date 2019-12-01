package com.wilson.cms.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Update;

import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.vo.SearchParam;

/**
 * 
 */

public interface IMaterialMapper {
    void add(MaterialPo item);
    List<MaterialPo> search(SearchParam args);
    @Update("delete from material  where id =#{id}")
    void  removeAt(Long id);
}