package com.wm.po;

import java.sql.Timestamp;

public class NewsPo  {
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
    private Long metaId;
    private String title;
    private String coverUrl;
    private String keyword;
    private String description;
    private String sourceUrl;
    private String source;
    private String tags;
    private String author;
   private Timestamp publishTime;
   private Timestamp offlineTime;
   private boolean isAutoPublish;
   private boolean isAutoOffline;
   private boolean isTop;
   private boolean isHot;
   private boolean isRecommend;
   private Short sort;
   private  String content;


    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Timestamp offlineTime) {
        this.offlineTime = offlineTime;
    }

    public boolean isAutoPublish() {
        return isAutoPublish;
    }

    public void setAutoPublish(boolean autoPublish) {
        isAutoPublish = autoPublish;
    }

    public boolean isAutoOffline() {
        return isAutoOffline;
    }

    public void setAutoOffline(boolean autoOffline) {
        isAutoOffline = autoOffline;
    }

    public boolean isTop() {
        return isTop;
    }

    public void setTop(boolean top) {
        isTop = top;
    }

    public boolean isHot() {
        return isHot;
    }

    public void setHot(boolean hot) {
        isHot = hot;
    }

    public boolean isRecommend() {
        return isRecommend;
    }

    public void setRecommend(boolean recommend) {
        isRecommend = recommend;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}