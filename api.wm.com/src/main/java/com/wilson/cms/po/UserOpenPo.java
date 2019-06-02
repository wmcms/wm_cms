package com.wilson.cms.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilson.cms.utils.Constant;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserOpenPo {

    private  Long id;
    private Long userId;
    private  String openId;
    private Short type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }
}