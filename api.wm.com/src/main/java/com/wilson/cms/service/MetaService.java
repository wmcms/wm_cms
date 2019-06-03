package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.IMetaMapper;
import com.wilson.cms.po.MetaPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.RequestParam;
import com.wilson.cms.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MetaService {

	@Autowired
    IMetaMapper iMetaMapper;

	/**
	 * 分页查询
	 * @param args  分页参数
	 * @return
	 */
	public PageResult<MetaPo> search(RequestParam args){
		PageHelper.startPage(args.getPageIndex(),args.getPageSize());
		List<MetaPo> list= iMetaMapper.search(args);
		PageInfo<MetaPo> pageInfo = new PageInfo<MetaPo>(list);
		PageResult<MetaPo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		pageInfo=null;
		return  result;
	}

	/**
	 * 所取所有基础数据
	 * @return
	 */
	public List<MetaPo> getAll(){
		List<MetaPo> list= iMetaMapper.search(new RequestParam());
		return  list;
	}

	/**
	 * 保存基础数据
	 * @param item
	 * @return
	 */
	public Result save(MetaPo item) {
		if (null == item.getId()) {
			item.setId(StringUtils.newLoginId(MetaPo.class));
			if (null != item.getParentId()) {
				item.setParentPath(StringUtils.isEmpty(item.getParentPath()) ? item.getParentId().toString()
						: item.getParentPath() + "," + item.getParentId());
			}
			iMetaMapper.add(item);
		} else {
			iMetaMapper.update(item);
		}
		return Result.Success(item.getId());
	}


}
