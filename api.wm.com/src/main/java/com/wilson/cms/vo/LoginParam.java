package com.wilson.cms.vo;

/**
 * @ClassName LoginParam
 * @Description 登录参数
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

public class LoginParam  {

   private String loginKey;
   private  String password;
   private  String validCode;
   private  String openId;
   private  LoginMethod method;

    public String getLoginKey() {
        return loginKey;
    }

    public void setLoginKey(String loginKey) {
        this.loginKey = loginKey;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidCode() {
        return validCode;
    }

    public void setValidCode(String validCode) {
        this.validCode = validCode;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public LoginMethod getMethod() {
        return method;
    }

    public void setMethod(LoginMethod method) {
        this.method = method;
    }
}