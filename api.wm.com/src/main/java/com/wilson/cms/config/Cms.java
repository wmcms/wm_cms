package com.wilson.cms.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;

/**
 * @ClassName Cms
 * @Description Cms配置项
 * @Author wilson
 * @Date 2019/5/29 20:52
 * @Version 1.0
 **/

@Configuration
@ConfigurationProperties(prefix = "cms")
public class Cms {

    /* 6位随机数*/
    private String slat;

    /**
     * 32位MD5随机字符串
     */
    private String token;

    /**
     * 用户类型
     */
    private List<Map<String,String>> userType;

    /**
     * 用户状态
     */
    private List<Map<String,String>> userStatus;

    /**
     * 字典类型
     */
    private List<Map<String,String>> meateType;

    public List<Map<String, String>> getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(List<Map<String, String>> userStatus) {
        this.userStatus = userStatus;
    }

    public List<Map<String, String>> getMeateType() {
        return meateType;
    }

    public void setMeateType(List<Map<String, String>> meateType) {
        this.meateType = meateType;
    }

    public List<Map<String, String>> getUserType() {
        return userType;
    }

    public void setUserType(List<Map<String, String>> userType) {
        this.userType = userType;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSlat() {
        return slat;
    }

    public void setSlat(String slat) {
        this.slat = slat;
    }

    public  Long initValue;

    public Long getInitValue() {
        return initValue;
    }

    public void setInitValue(Long initValue) {
        this.initValue = initValue;
    }
}
