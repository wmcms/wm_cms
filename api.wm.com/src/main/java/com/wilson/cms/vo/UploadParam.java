package com.wilson.cms.vo;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;


@SuppressWarnings("serial")
public class UploadParam implements Serializable {

    private  String name;
    private Long metaId;
    private  MultipartFile[] files;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
}