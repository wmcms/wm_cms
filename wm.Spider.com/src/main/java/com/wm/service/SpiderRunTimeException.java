package com.wm.service;

import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 * Created by CHENT
 * <p>
 * 自定义爬虫项目异常
 */

public class SpiderRunTimeException extends RuntimeException {

    /**
     * Exception错误码 ,
     * 1. 如为 HTTP请求异常 则为 <b>当前 HTTP状态码</b>
     * 2. 如为 代码逻辑异常 则为 <b>RespondCode类 枚举值</b>
     * 3. 如为 运行时异常   则为 <b>-1<b/>
     */
    private Integer errorCode;

    /**
     * 程序抛出异常时的错误描述
     */
    private String errorMessage;


    /**
     * SpiderRunTimeException 构造方法
     *
     * @param errorCode        Exception错误码
     * @param errorMessage     程序抛出异常时的错误描述
     */

    public SpiderRunTimeException(Integer errorCode ,String errorMessage ){
        this.errorMessage=errorMessage;
        this.errorCode=errorCode;
    }


    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        return "SpiderRunTimeException{" +
            "errorCode=" + errorCode +
            ", errorMessage='" + errorMessage + '\'' +
            '}';
    }
}