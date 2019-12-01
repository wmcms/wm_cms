package com.wilson.cms.mapper;

import com.wilson.cms.po.UserBehaviorPo;
import com.wilson.cms.vo.SearchParam;
import com.wilson.cms.vo.UserBehaviorVo;

import java.util.List;

/**
 *
 */

public interface IUserBehaviorMapper {

    public List<UserBehaviorVo> search(SearchParam args);

    void update(UserBehaviorPo item);

    void add(UserBehaviorPo user);
}