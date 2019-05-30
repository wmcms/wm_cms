package com.wilson.cms.mapper;

import com.wilson.cms.po.TMeta;
import com.wilson.cms.vo.Meta;
import com.wilson.cms.vo.RequestArgs;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMetaMapper {
    List<Meta> searchMeta(RequestArgs args);
    void update(TMeta item);
    void  batchDelete(List<Long> Ids);
    void add(TMeta item);
    @Select("select * from meta where meta_id =#{id}")
    TMeta getById(long id);
    List<TMeta> getAllMeta();
}