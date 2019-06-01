package com.wilson.cms.mapper;
import com.wilson.cms.po.UserPo;

public interface IUserMapper {
    /**
     *根据帐号查询登录用户
     * 账号可以是：手机号，用户名 等
     * @param id
     * @param keyword
     * @return
     */
    public UserPo queryExists(Long id, String keyword);

    /**
     * 注册帐号
     * @param user
     */
    void register(UserPo user);

}