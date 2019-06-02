package com.wilson.cms.vo;

import java.io.Serializable;
import java.util.List;

public class UserVo implements Serializable {

    private  String name;
    private  String nickname;
    private  String headUrl;
    private List<String> rose;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public List<String> getRose() {
        return rose;
    }

    public void setRose(List<String> rose) {
        this.rose = rose;
    }
}