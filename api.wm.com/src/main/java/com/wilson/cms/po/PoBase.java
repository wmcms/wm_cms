package com.wilson.cms.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * 实体基类
 * 定义所有表的公共字段
 */
public class PoBase implements Serializable {

    public PoBase() {
        this.createTime = Timestamp.valueOf(LocalDateTime.now());
        this.updateTime=Timestamp.valueOf(LocalDateTime.now());
        this.status=0;
    }

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
    protected Short status;

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

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }
}