package com.wilson.cms.mapper;

import com.wilson.cms.po.TUser;
import com.wilson.cms.po.UserInfoPo;
import com.wilson.cms.po.UserPo;
import com.wilson.cms.vo.RequestArgs;
import com.wilson.cms.vo.UserVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IUserInfoMapper {
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
    List<UserInfoPo> searchUser(RequestArgs args);

    /**
     * 更新用户
     * @param item
     */
    void update(UserInfoPo item);

    /**
     * 批量删除
     * @param userIds
     */
    void  batchDelete(List<Long> userIds);

    /**
     * 新增用户
     * @param item
     */
    void add(UserInfoPo item);

    UserInfoPo getUser(Long id);

    /**
     * 根据主键获取用户
     * @param id
     * @return
     */
    @Select("select * from user where id =#{ID}")
    TUser get(long id);
}