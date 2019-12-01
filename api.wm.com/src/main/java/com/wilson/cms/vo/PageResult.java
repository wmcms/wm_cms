package com.wilson.cms.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 响应
 */
@SuppressWarnings("serial")
public class PageResult<T> implements Serializable {

    private List<T> Items;

    private  long Total;

    public List<T> getItems() {
        return Items;
    }

    public void setItems(List<T> items) {
        Items = items;
    }

    public long getTotal() {
        return Total;
    }

    public void setTotal(long total) {
        Total = total;
    }
}