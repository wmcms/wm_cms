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
    private Long metaId;
    private String fileName;
    private  String fileType;
    private Long fileSize;
    private String url;

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

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
