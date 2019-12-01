package com.wilson.cms.vo;

/**
 * @ClassName RegisterParam
 * @Description 注册参数
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

public class RegisterParam {

	private String accountId;
	private String nickname;
	private String password;
	private String imgCode;
	private String imgCodeId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImgCode() {
		return imgCode;
	}

	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}

	public String getImgCodeId() {
		return imgCodeId;
	}

	public void setImgCodeId(String imgCodeId) {
		this.imgCodeId = imgCodeId;
	}

}