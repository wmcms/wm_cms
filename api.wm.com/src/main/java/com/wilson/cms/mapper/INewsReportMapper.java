package com.wilson.cms.mapper;


import java.util.Map;

import com.wilson.cms.po.NewsReportPo;

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