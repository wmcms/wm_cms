package com.wilson.cms.vo;

import java.io.Serializable;

/**
 * 分页查询参数
 */
@SuppressWarnings("serial")
public class RequestParam implements Serializable {
    /**
     * 页码
     */
    private Integer pageIndex;
    /**
     * 每页条目数
     */
    private Integer pageSize;

    /**
     * 类型
     */
    private  Short type;
    /**
     * 关键字
     */
    private  String keyword;


    private  Long targetId;

    private  BehaviorType behaviorType;

    private  Long metaId;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }
}