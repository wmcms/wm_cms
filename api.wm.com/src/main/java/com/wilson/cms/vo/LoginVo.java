package com.wilson.cms.vo;

import java.io.Serializable;


public class LoginVo implements Serializable {
   private String userName;
   private  String password;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}