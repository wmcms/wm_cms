package com.wilson.cms.po;

import com.wilson.cms.config.Cms;
import com.wilson.cms.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserPo  {

  private Long id;
  private String loginKey;
  private  String password;
  private  String slat;
  private  Short status;

    public UserPo() {
        this.id = StringUtils.newLoginId(UserPo.class);
        this.slat =StringUtils.newImgCode();
        this.status=0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}