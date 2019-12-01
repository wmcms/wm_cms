package com.wilson.cms.vo;

/**
 * @ClassName DataType
 * @Description 枚举类型
 * @Author wilson
 * @Date 2019/5/29 21:53
 * @Version 1.0
 **/
public enum DataType implements IEnum {

    newscategory(1,"文章类别"),
    adslot(2,"广告位置"),
    adype(3,"广告类型"),
    materialtype(4,"素材分类");
    private Integer key;
    private String text;

    DataType(Integer key, String text) {
        this.key =key;
        this.text = text;
    }

    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getText() {
        return this.text;
    }
}
