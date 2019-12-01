package com.wm.mapper;

import java.util.List;

import com.wm.po.NewsPo;

public interface INewsMapper {
    void add(NewsPo item);
    void update(NewsPo item);
}