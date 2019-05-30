package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.*;
import com.wilson.cms.po.*;
import com.wilson.cms.vo.Meta;
import com.wilson.cms.vo.RequestArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetaService implements IService<TMeta>{

	@Autowired
    IMetaMapper iMetaMapper;

	/**
	 * 分页查询
	 * @param page  分页参数
	 * @param username 用户名
	 * @return
	 */
	public PageInfo<Meta> searchWithPageList(RequestArgs args){
		PageHelper.startPage(args.getPageIndex(),args.getPageSize());
		List<Meta> list= iMetaMapper.searchMeta(args);
		System.out.println(list);
		PageInfo<Meta> pageInfo = new PageInfo<Meta>(list);
		return  pageInfo;
	}

	/**
	 * 批量删除数据
	 * @param ids
	 */
	public  void  batchDelete(List<Long> ids){
		iMetaMapper.batchDelete(ids);
	}

	public List<TMeta> getAllMeta(){
		List<TMeta> list= iMetaMapper.getAllMeta();
		return  list;
	}


	@Override
	public TMeta getById(Long metaId) {
		return  iMetaMapper.getById(metaId);
	}
	@Override
	public void add(TMeta item) {
		iMetaMapper.add(item);

	}

	@Override
	public	void updateById(TMeta item){
		iMetaMapper.update(item);
	}

}
