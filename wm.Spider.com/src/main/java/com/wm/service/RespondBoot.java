package com.wm.service;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by CHENT
 */

//implements Serializable 实现序列化接口便于网络传输
public class RespondBoot<T> implements Serializable {

    //注意无论返回什么必须要有一个状态码~ *
    private int status;
    //普通的字符串响应简单数据 *
    private String msg;
    //泛型化可响应复杂数据,根据请求处理结果 来判断封装什么数据 String,List,Map等也可不返回数据,根据构造方法来定~ *
    private T data;

    //将构造方法private 后不能New此对象,只能调用此类提供 public 方法获得对象,
    // 调用用原有构造不能返回String数据,必须用名字封装一层
    private RespondBoot(int status) {
        this.status = status;
    }

    private RespondBoot(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    //处理复杂数据时通过 public方法规避调用String成员变量 *
    private RespondBoot(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private RespondBoot(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //自定义枚举类校验当前状态是否成功 SUCCESS(1,"SUCCESS")
    @JsonIgnore //使之不在JSON序列化结果中
    public boolean isSuccess() {
        //==后相当于RespondCode类中SUCCESS 静态类的getCode方法
        return this.status == RespondCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }


    //封装好后提供给外部使用的public对象构造函数
    public static <T> RespondBoot<T> createBySuccess() {
        return new RespondBoot<T>(RespondCode.SUCCESS.getCode());
    }

    //注意这里方法名和其他处不一样如果需要返回 status和smg(消息) 就单独调用此方法.不受泛型干扰
    public static <T> RespondBoot<T> createBySuccessMessage(String msg) {
        return new RespondBoot<T>(RespondCode.SUCCESS.getCode(), msg);
    }

    //经过上面的处理也可以用data泛型返回String(数据)了
    public static <T> RespondBoot<T> createBySuccess(T data) {
        return new RespondBoot<T>(RespondCode.SUCCESS.getCode(), data);
    }

    public static <T> RespondBoot<T> createBySuccess(String msg, T data) {
        return new RespondBoot<T>(RespondCode.SUCCESS.getCode(), msg, data);
    }

    //空参则返回枚举Code和Desc信息
    public static <T> RespondBoot<T> createByError() {
        return new RespondBoot<T>(RespondCode.ERORR.getCode(), RespondCode.ERORR.getDesc());
    }

    //满参: errorMessage则返回枚举Code和自定义errorMessage
    public static <T> RespondBoot<T> createByErrorMessage(String errorMessage) {
        return new RespondBoot<T>(RespondCode.ERORR.getCode(), errorMessage);
    }

    //满参 :errorCode+ errorMessage则返回自定义errorCode, 自定义errorMessage
    public static <T> RespondBoot<T> createByErrorCodeMessage(int errorCode, String errorMessage) {
        return new RespondBoot<T>(errorCode, errorMessage);
    }
}