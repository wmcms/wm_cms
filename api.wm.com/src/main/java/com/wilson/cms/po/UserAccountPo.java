package com.wilson.cms.po;

public class UserAccountPo {

	public UserAccountPo() {
		this.createTime = System.currentTimeMillis();
		this.updateTime = System.currentTimeMillis();
		this.status = 0;
	}

	private Long userId;
	private String id;
	private Integer type;
	private Integer authMethod;
	private String password;
	private Long createTime;
	private Long updateTime;
	private Integer status;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getAuthMethod() {
		return authMethod;
	}

	public void setAuthMethod(Integer authMethod) {
		this.authMethod = authMethod;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}