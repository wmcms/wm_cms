package com.wilson.cms.po;

import com.wilson.cms.vo.BehaviorType;


/**
 * @ClassName UserBehavior
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 13:12
 * @Version 1.0
 **/
public class UserBehaviorPo extends PoBase {

  
    private BehaviorType behaviorType;
    private Long targetId;
    private Integer targetType;
    private String title;
    private Long userId;
    private  String content;
    private  String ip;
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
	public Integer getTargetType() {
		return targetType;
	}
	public void setTargetType(Integer targetType) {
		this.targetType = targetType;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
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

   
}
