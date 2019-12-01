package com.wilson.cms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wilson.cms.mapper.INewsContentMapper;
import com.wilson.cms.mapper.INewsMapper;
import com.wilson.cms.mapper.INewsReportMapper;
import com.wilson.cms.mapper.IVoteMapper;
import com.wilson.cms.po.NewsPo;
import com.wilson.cms.po.NewsReportPo;
import com.wilson.cms.po.VotePo;
import com.wilson.cms.utils.JsonUtils;
import com.wilson.cms.utils.StringUtils;
import com.wilson.cms.vo.News;
import com.wilson.cms.vo.PageResult;
import com.wilson.cms.vo.Result;
import com.wilson.cms.vo.SearchParam;

/**
 * @ClassName NewsService
 * @Description TODO
 * @Author wilson
 * @Date 2019/6/2 0:30
 * @Version 1.0
 **/
@Service
public class NewsService {

	@Autowired
	INewsContentMapper iNewsContentMapper;
	@Autowired
	INewsMapper iNewsMapper;
	@Autowired
	IVoteMapper iVoteMapper;
	@Autowired
	INewsReportMapper iNewsReportMapper;

	/**
	 * 文章列表查询
	 * 
	 * @param args
	 * @return
	 */
	public PageResult<NewsPo> search(SearchParam args) {
		PageHelper.startPage(args.getPageIndex(), args.getPageSize());
		List<NewsPo> list = iNewsMapper.search(args);
		PageInfo<NewsPo> pageInfo = new PageInfo<NewsPo>(list);
		PageResult<NewsPo> result = new PageResult<>();
		result.setItems(pageInfo.getList());
		result.setTotal(pageInfo.getTotal());
		pageInfo = null;
		return result;
	}

	/**
	 * 获取文章
	 * 
	 * @param id
	 * @return
	 */
	@Transactional
	public News getById(Long id) {
		News news = new News();
		if (id > 0) {
			NewsPo newsPo = iNewsMapper.get(id);
			List<VotePo> votes = iVoteMapper.getAll(id);
			NewsReportPo newsReportPo = iNewsReportMapper.get(id);
			news.setNews(newsPo);
			news.setNewsReportPo(newsReportPo);

			if (votes == null) {
				news.setVotes(new ArrayList<>());
			} else {
				news.setVotes(votes);
			}
		} else {
			news.setNews(new NewsPo());
			news.setVotes(new ArrayList<>());
		}
		return news;
	}

	public void addVisitCount(Long id) {
		// 增加浏览量
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("key", "visit_count");
		iNewsReportMapper.save(map);
	}

	/**
	 * 保存文章
	 * 
	 * @param item
	 * @return
	 */
	@Transactional
	public Result save(News item) {
		System.out.println(JsonUtils.obj2String(item));
		NewsPo news = item.getNews();
		List<VotePo> votes = item.getVotes();
		if (news.getId() == null) {
			news.setId(StringUtils.newLongId(NewsPo.class));
			iNewsMapper.add(news);
		} else {
			news.setUpdateUserId(news.getCreateUserId());
			iNewsMapper.update(news);
		}
		if (news.getContent() != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", news.getId());
			map.put("text", news.getContent());
			iNewsContentMapper.save(map);
		}
		if (votes != null && votes.size() > 0) {
			iVoteMapper.removeAt(news.getId());
			for (VotePo vote : votes) {
				// if(vote.getId()==null) {
				vote.setId(StringUtils.newLongId(VotePo.class));
				vote.setTargetId(news.getId());
				vote.setCreateUserId(news.getCreateUserId());
				iVoteMapper.add(vote);
//                }
//                else{
//                    vote.setUpdateUserId(news.getCreateUserId());
//                    iVoteMapper.update(vote);
//                }
			}
		}

		return Result.Success(news.getId());
	}

	@Transactional
	public void add(NewsPo news) {
		news.setId(StringUtils.newLongId(NewsPo.class));
		iNewsMapper.add(news);
		if (news.getContent() != null) {
			Map<String, Object> map = new HashMap<>();
			map.put("id", news.getId());
			map.put("text", news.getContent());
			iNewsContentMapper.save(map);
		}

	}

}
