package com.wilson.cms.mapper;
import com.wilson.cms.po.UserAccountPo;
import org.apache.ibatis.annotations.Update;

public interface IUserAccountMapper {
    /**
     *根据帐号查询登录用户
     * 账号可以是：手机号，用户名 等
     * @param id
     * @return
     */
    public UserAccountPo getById( String id);

    /**
     * 注册帐号
     * @param user
     */
    void register(UserAccountPo user);

    /**
     * 修改用户信息
     * @param user
     */
    void update(UserAccountPo user);


}