package com.wilson.cms.mapper;

import com.wilson.cms.po.TUser;
import com.wilson.cms.vo.RequestParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserReportMapper {
    /**
     *根据帐号查询登录用户
     * 账号可以是：手机号，用户名 等
     * @param id
     * @param keyword
     * @return
     */
    public TUser queryExists(Long id, String keyword);

    /**
     * 用户列表查询
     * @param args 查询参数
     * @return 用户列表
     */
    List<TUser> searchUser(RequestParam args);

    /**
     * 更新用户
     * @param item
     */
    void update(TUser item);

    /**
     * 批量删除
     * @param userIds
     */
    void  batchDelete(List<Long> userIds);

    /**
     * 新增用户
     * @param user
     */
    void add(TUser user);

    /**
     * 根据主键获取用户
     * @param id
     * @return
     */
    @Select("select * from user where id =#{ID}")
    TUser get(long id);
}