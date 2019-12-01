package com.wilson.cms.vo;

import java.io.Serializable;

/**
 * 响应
 */
@SuppressWarnings("serial")
public class Result implements Serializable {

    /**
     * 是否执行成功
     */
    private  boolean success;

    /**
     * 响应数据
     */
    private  Object data;

    /**
     * 编码
     */
    private   int code;

    /**
     * 消息文本
     */
    private  String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    private Result() {
    }

    private Result(int code ) {
        this.success= true;
        this.code = code;
    }
    private Result(int code,String msg ) {
        this(code);
        this.msg= msg;
    }

    public  static Result Success(Object data) {
        Result result = new Result(200);
        result.setData(data);
        return result;
    }
    public  static Result Error(String msg){
        return new Result(405,msg);
    }
    public  static Result NoLogin(String msg){
        return new Result(402,msg);
    }

    public  static Result NoPermission(String msg){
        return new Result(403,msg);
    }

    public  static Result Execption(String msg){
        Result result = new Result(500, msg);
        result.setSuccess(false);
        return  result;
    }
}