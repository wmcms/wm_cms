package com.wilson.cms.vo;

/**
 * @ClassName LoginParam
 * @Description 登录参数
 * @Author wilson
 * @Date 2019/5/27 22:57
 * @Version 1.0
 **/

public class LoginParam  {

   private String accountId;
   private  String password;
   private  String imgCode;
   private  String smsCode;
   private  String unionId;
   private  LoginMethod method;
public String getAccountId() {
	return accountId;
}
public void setAccountId(String accountId) {
	this.accountId = accountId;
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
public String getSmsCode() {
	return smsCode;
}
public void setSmsCode(String smsCode) {
	this.smsCode = smsCode;
}
public String getUnionId() {
	return unionId;
}
public void setUnionId(String unionId) {
	this.unionId = unionId;
}
public LoginMethod getMethod() {
	return method;
}
public void setMethod(LoginMethod method) {
	this.method = method;
}

   
}