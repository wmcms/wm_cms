package com.wilson.cms.mapper;

import com.wilson.cms.po.MaterialPo;
import com.wilson.cms.vo.RequestArgs;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 
 */

public interface IMaterialMapper {
    void add(MaterialPo item);
    List<MaterialPo> search(RequestArgs args);
    @Update("update material set status=-1 where id in(${ids})")
    void  removeAt(String ids);
}