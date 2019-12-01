package com.wilson.cms.mapper;

import java.util.List;

import com.wilson.cms.po.UserPo;
import com.wilson.cms.vo.SearchParam;

public interface IUserMapper {

    /**
     * 用户列表查询
     * @param args 查询参数
     * @return 用户列表
     */
    List<UserPo> searchUser(SearchParam args);

    /**
     * 更新用户
     * @param item
     */
    void update(UserPo item);

    /**
     * 批量删除
     * @param userIds
     */
    void  batchDelete(List<Long> userIds);

    /**
     * 新增用户
     * @param item
     */
    void add(UserPo item);

    UserPo getUser(Long id);


}