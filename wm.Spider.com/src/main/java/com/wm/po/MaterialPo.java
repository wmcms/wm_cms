package com.wm.po;

import java.sql.Timestamp;

/**
 * @ClassName MaterialPo
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 21:04
 * @Version 1.0
 **/
public class MaterialPo  {
   
	 /**
     * 主键
     */
    protected  Long id;
    /**
     * 创建时间
     */
    protected Timestamp createTime;
    /**
     * 创建人
     */
    protected  Long createUserId;
    /**
     * 修改时间
     */
    protected Timestamp updateTime;
    /**
     * 修改人
     */
    protected  Long updateUserId;
    /**
     * 数据状态
     */
    protected Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
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
