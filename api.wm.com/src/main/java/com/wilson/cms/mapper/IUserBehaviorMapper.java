package com.wilson.cms.mapper;

import com.wilson.cms.po.UserBehaviorPo;
import com.wilson.cms.vo.RequestParam;

import java.util.List;

/**
 *
 */

public interface IUserBehaviorMapper {

    public List<UserBehaviorPo> search(RequestParam args);

    void update(UserBehaviorPo item);

    void add(UserBehaviorPo user);
}