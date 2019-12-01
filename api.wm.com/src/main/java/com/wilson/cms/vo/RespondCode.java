package com.wilson.cms.vo;


/**
 * Created by CHENT
 * 枚举常量默认被public Static final隐式转换
 *
 *
 */
public  enum RespondCode {
    // 声明枚举变量隐式于public static final RespondCode类SUCCESS引用指向
    //  了在static{ SUCCESS=new RespondCode(0,"SUCCESS") }

    // 0 成功Code
    SUCCESS(1,"SUCCESS"),
    // 1 错误Code
    ERORR(0,"ERORR"),
    // 2 非法参数Code
    ILLEGAL_ARGUMENT(2,"非法参数 !"),
    // 3 抓取页面错误
    DOWNLOADS_ERORR(3,"请求页面异常 !"),
    // 4 页面构建失败
    CONSTRUCTION_ERORR(4,"页面构建失败 !")
    // -1 程序运行发生未知错误
,    RUNTIME_ERORR(-1,"程序运行发生未知错误!"),
    // 10 未登录Code
    NEED_LOGIN(10,"未登录 !");




    // 只提供Get方法不可在运行时修改原值
    private final  int code;
    private final  String desc;

    // 反编译后发现枚举常量如SUCCESS相当于的public static final RespondCode 对象的类引用,后面的参数则是向构造方法里传参~
    RespondCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
