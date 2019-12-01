package com.wilson.cms.vo;

/**
 * @ClassName MateridalVo
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/22 13:32
 * @Version 1.0
 **/
public class FileVo {

    private  String name;
    private  Long size;
    private  String type;
    private  String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
