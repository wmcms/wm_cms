package com.wilson.cms.po;

import com.wilson.cms.vo.BehaviorType;

import java.sql.Timestamp;

/**
 * @ClassName UserBehavior
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 13:12
 * @Version 1.0
 **/
public class UserBehaviorPo {

    private Long id;
    private BehaviorType behaviorType;
    private Long targetId;
    private Long userId;
    private Short status;
    private Timestamp createTime;
    private  String content;
    private  String ip;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BehaviorType getBehaviorType() {
        return behaviorType;
    }

    public void setBehaviorType(BehaviorType behaviorType) {
        this.behaviorType = behaviorType;
    }

    public Long getTargetId() {
        return targetId;
    }

    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Short getStatus() {
        return status;
    }

    public void setStatus(Short status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
