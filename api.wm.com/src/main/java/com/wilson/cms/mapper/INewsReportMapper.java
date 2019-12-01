package com.wilson.cms.mapper;


import com.wilson.cms.po.NewsReportPo;

import java.util.List;
import java.util.Map;

/**
 * 
 */

public interface INewsReportMapper {

    void save(Map<String, Object> item);

    /**
     * 根据主键获取用户
     *
     * @param id
     * @return
     */
    NewsReportPo get(long id);

}