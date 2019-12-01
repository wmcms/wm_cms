package com.wilson.cms.mapper;

import com.wilson.cms.po.VotePo;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface IVoteMapper {

    void add(VotePo item);
    void update(VotePo item);
    @Delete("delete from vote where target_id=#{targetId}")
    void removeAt(Long targetId);
    List<VotePo> getAll(Long targetId);
}