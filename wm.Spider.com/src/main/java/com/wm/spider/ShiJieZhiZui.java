package com.wm.spider;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wm.mapper.TodaySpiderDispatcher;
import com.wm.po.NewsPo;
import com.wm.po.SpiderFruit;
import com.wm.service.NewsService;
import com.wm.utils.DownloadsUtils;
import com.wm.utils.SpidertUtils;

public class ShiJieZhiZui implements Job{
	@Autowired NewsService newsService;
	private static final Logger logger = LoggerFactory.getLogger(TodaySpiderDispatcher.class);
	 public void execute(JobExecutionContext context) throws JobExecutionException {
	      
		  // 定时抓取 原始页面
	        SpiderFruit initializeHtml = null;
			try {
				initializeHtml = DownloadsUtils.getPageContent("https://www.dugoogle.com/shijiezhizui/products/");
			} catch (IOException e) {
				return;
			}
	        List<NewsPo> newsList = null;
			try {
				newsList = SpidertUtils.scanToList(initializeHtml.getEntitystr());			
			} catch (IOException e) {				
			}	       
			
			if (null != newsList) {				
				for (NewsPo item : newsList) {
					try {
						initializeHtml = DownloadsUtils.getPageContent("https://www.dugoogle.com"+item.getSourceUrl());
					} catch (IOException e) {
						continue;
					}
					try {
						SpidertUtils.scanToItem(initializeHtml.getEntitystr(),item);
						System.out.println(item.getContent());
					} catch (IOException e) {	
					
						newsService.save(item);
						
					}
				}
				
			}
	      
		 
	    }
	}