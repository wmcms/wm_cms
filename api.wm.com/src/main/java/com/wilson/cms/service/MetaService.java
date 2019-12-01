package com.wilson.cms.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.IMetaMapper;
import com.wilson.cms.po.MetaPo;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.SearchParam;
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
	public PageResult<MetaPo> search(SearchParam args){
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
		List<MetaPo> list= iMetaMapper.search(new SearchParam());
		return  list;
	}


	public  Result remove(Long userId,List<Long> idList){

		MetaPo metaPo = null;
		for (Long id: idList ) {
			metaPo = new MetaPo();
			metaPo.setId(id);
			metaPo.setStatus(-1);
			metaPo.setUpdateUserId(userId);
			save(metaPo);
		}
		return  Result.Success(idList.size());
	}

	/**
	 * 保存基础数据
	 * @param item
	 * @return
	 */
	public Result save(MetaPo item) {
		if (item.getId()>0) {
			iMetaMapper.update(item);
		} else {
			item.setId(StringUtils.newLongId(MetaPo.class));
			if (null != item.getParentId()) {
				item.setParentPath(StringUtils.isEmpty(item.getParentPath()) ? item.getParentId().toString()
						: item.getParentPath() + "," + item.getParentId());
			}
			iMetaMapper.add(item);
		}
		return Result.Success(item.getId());
	}


}
