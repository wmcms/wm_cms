package com.wilson.cms;

import java.io.IOException;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.wilson.cms.po.NewsPo;
import com.wilson.cms.utils.DownloadsUtils;
import com.wilson.cms.utils.SpidertUtils;
import com.wilson.cms.vo.SpiderFruit;

public class SpiderJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {

		for (int i = 1; i < 40; i++) {

			// 定时抓取 原始页面
			SpiderFruit initializeHtml = null;
			try {
				String url ="https://www.dugoogle.com/shijiezhizui/products/";
				if(i>1)
					url +=String.format("index_%d.html", i);
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
						initializeHtml = DownloadsUtils
								.getPageContent("https://www.dugoogle.com" + item.getSourceUrl());
					} catch (IOException e) {
						continue;
					}
					try {
						SpidertUtils.scanToItem(initializeHtml.getEntitystr(), item);
						System.out.println(item.getContent());
					} catch (IOException e) {
						continue;

					}
					item.setMetaId(1906032244030001L);
					SpidertUtils.DBService.add(item);
				}

			}

		}
	}
}