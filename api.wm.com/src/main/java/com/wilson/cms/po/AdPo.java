package com.wilson.cms.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilson.cms.utils.Constant;
import java.util.Date;

public class AdPo extends PoBase {

    private  Long metaId;
    private String name;
   @JsonFormat(pattern = Constant.DATE_FORMAT,timezone = Constant.TIME_ZONE)
   private Date beginTime;
    @JsonFormat(pattern = Constant.DATE_FORMAT,timezone =  Constant.TIME_ZONE)
    private Date endTime;
    private  String url;
    private Short type;
    private  Short sort;
    private  String resUrl;

    public Long getMetaId() {
        return metaId;
    }

    public void setMetaId(Long metaId) {
        this.metaId = metaId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getResUrl() {
        return resUrl;
    }

    public void setResUrl(String resUrl) {
        this.resUrl = resUrl;
    }
}