package com.wilson.cms.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilson.cms.utils.UConstant;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class UserPo extends TEntity {

    private String name;
    private String password;
    private  String mobile;
    private  String email;
    private char gender;
    private  String nickname;
    private  String realName;
    private  String slat;
    @JsonFormat(pattern = UConstant.DATE_FORMAT)
    @DateTimeFormat(pattern = UConstant.DATE_FORMAT)
    private Date birthDate;
    private Short activateStatus;
    private Short level;
    private Short type;
    private  String headUrl;

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Short getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(Short activateStatus) {
        this.activateStatus = activateStatus;
    }

    public Short getLevel() {
        return level;
    }

    public void setLevel(Short level) {
        this.level = level;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TUser{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", gender=" + gender +
                ", nickname='" + nickname + '\'' +
                ", realName='" + realName + '\'' +
                ", slat='" + slat + '\'' +
                ", birthDate=" + birthDate +
                ", activateStatus=" + activateStatus +
                ", level=" + level +
                ", type=" + type +
                ", id=" + id +
                ", createTime=" + createTime +
                ", createUserId=" + createUserId +
                ", updateTime=" + updateTime +
                ", updateUserId=" + updateUserId +
                ", status=" + status +
                '}';
    }
}