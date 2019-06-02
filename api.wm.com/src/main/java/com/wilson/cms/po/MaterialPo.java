package com.wilson.cms.po;

/**
 * @ClassName MaterialPo
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 21:04
 * @Version 1.0
 **/
public class MaterialPo extends PoBase {
    private  String name;
    private Long categoryId;
    private String fileName;
    private  String fileType;
    private Long fileSzie;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Long getFileSzie() {
        return fileSzie;
    }

    public void setFileSzie(Long fileSzie) {
        this.fileSzie = fileSzie;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
