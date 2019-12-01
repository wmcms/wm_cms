package com.wilson.cms.po;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NewsPo extends PoBase {
	
    private Long metaId;
    private String title;
    private String coverUrl;
    private String keyword;
    private String description;
    private String sourceUrl;
    private String source;
    private String tags;
    private String author;
   private Long publishTime;
   private Long offlineTime;
   @JsonProperty("isAutoPublish")
   private boolean isAutoPublish;   
   @JsonProperty("isAutoOffline")
   private boolean isAutoOffline;
   @JsonProperty("isTop")
   private boolean isTop;
   @JsonProperty("isHot")
   private boolean isHot;
   @JsonProperty("isRecommend")
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

    public Long getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Long publishTime) {
        this.publishTime = publishTime;
    }

    public Long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(Long offlineTime) {
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