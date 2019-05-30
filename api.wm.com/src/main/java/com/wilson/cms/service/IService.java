package com.wilson.cms.service;

import com.wilson.cms.po.TUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 抽象类IService
* @ClassName: IService
* @Description: TODO(Service实现这个)
* @author fuce
* @date 2018年6月3日
*
 */
public interface IService<T> {
    /**
     * 新增
     * @param item
     */
    void add(T item);

    /**
     * 根据主键获取一条数据
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 根据主键更新数据
     * @param item
     */
    void updateById(T item);


}
