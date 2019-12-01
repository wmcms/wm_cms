package com.wilson.cms.po;

public class VotePo extends PoBase {

    private  Long targetId;
    private  String url;
    private  String title;
    private  String description;
    private Long beginTime;
    private  Long endTime;
    private  Short interval;
    private  Short maxSelected;
    private  Short mode;
    private  Short sort;
    private  Integer total;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Long beginTime) {
        this.beginTime = beginTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public Short getInterval() {
        return interval;
    }

    public void setInterval(Short interval) {
        this.interval = interval;
    }

    public Short getMaxSelected() {
        return maxSelected;
    }

    public void setMaxSelected(Short maxSelected) {
        this.maxSelected = maxSelected;
    }

    public Short getMode() {
        return mode;
    }

    public void setMode(Short mode) {
        this.mode = mode;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }
}