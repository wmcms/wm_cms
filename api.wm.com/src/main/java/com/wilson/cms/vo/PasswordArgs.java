package com.wilson.cms.vo;

import java.io.Serializable;


public class PasswordArgs implements Serializable {
   private  Long userId;
   private  String password;
   private  String origPassword;
   private  String slat;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOrigPassword() {
        return origPassword;
    }

    public void setOrigPassword(String origPassword) {
        this.origPassword = origPassword;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }
}