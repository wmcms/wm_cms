package com.wilson.cms.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SmsCodeParam implements Serializable {
	private String imgCodeId;
	private String imgCode;
	private String mobile;
	public String getImgCodeId() {
		return imgCodeId;
	}
	public void setImgCodeId(String imgCodeId) {
		this.imgCodeId = imgCodeId;
	}
	public String getImgCode() {
		return imgCode;
	}
	public void setImgCode(String imgCode) {
		this.imgCode = imgCode;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


}