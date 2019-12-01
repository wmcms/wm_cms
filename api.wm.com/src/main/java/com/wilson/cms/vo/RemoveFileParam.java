package com.wilson.cms.vo;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;


public class RemoveFileParam implements Serializable {

    private  String url;
    private Long id;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}