package com.wilson.cms.vo;

import java.io.Serializable;
import java.util.List;

public class ItemVo<T> implements Serializable {

    private T key;
    private  String text;

    public ItemVo(T key, String text) {
        this.key = key;
        this.text = text;
    }

    public T getKey() {
        return key;
    }

    public void setKey(T key) {
        this.key = key;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}