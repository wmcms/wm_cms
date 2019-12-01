package com.wilson.cms.po;


/**
 * 实体基类
 * 定义所有表的公共字段
 */
public class PoBase {

    public PoBase() {
        this.createTime = System.currentTimeMillis(); 
        this.updateTime=System.currentTimeMillis();
        this.status=0;
    }

    /**
     * 主键
     */
    protected  Long id;
    /**
     * 创建时间
     */
    protected Long createTime;
    /**
     * 创建人
     */
    protected  Long createUserId;
    /**
     * 修改时间
     */
    protected Long updateTime;
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

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
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
}