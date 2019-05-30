package com.wilson.cms.vo;

import java.io.Serializable;

/**
 * 响应
 */
public class AjaxResult  implements Serializable {

    /**
     * 是否执行成功
     */
    private  boolean success;

    /**
     * 响应数据
     */
    private  Object data;
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

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    private AjaxResult() {
       this(false,null,null);
    }
    private AjaxResult(boolean success, Object data, String msg) {
        this.success = success;
        this.data = data;
        this.msg = msg;
    }

    public  static  AjaxResult Success(Object data) {
        AjaxResult result = AjaxResult.Error(null);
        result.setCode(200);
        result.setSuccess(true);
        result.setData(data);
        return result;
    }
    public  static  AjaxResult Error(String msg){
        AjaxResult result = new AjaxResult();
        result.setCode(500);
        result.setMsg(msg);
        return  result;
    }
    public  static  AjaxResult PermissionError(String msg){
        AjaxResult result = Error(msg);
        result.setCode(403);
        return  result;
    }
}