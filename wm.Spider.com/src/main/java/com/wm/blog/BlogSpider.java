package com.wm.blog;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


//望同学举一反三~  

public class BlogSpider {

	public static void main(String[] args) throws IOException {

        Integer count = 1;

        // SpringBoot 默认集成LogBack
         Logger logger = LoggerFactory.getLogger(BlogSpider.class);

        logger.info("\n\r"+"---------------------开始抓取页面----------------------"+"\n\r");

        // 解析拿到最大页码
        String MaxSum = BlogTentacleUtils.Aherf_Page_Max("https://www.cnblogs.com/pick/#p1","#paging_block .pager a",12);

        // 解析全部分页
        String URLX = "https://www.cnblogs.com/mvc/AggSite/PostList.aspx";

        for (int x = 0 ; x < Integer.valueOf(MaxSum);){

            // 设置 POST 参数
            Map<String, Object> map = new HashMap<>();
            map.put("CategoryId",-2);
            map.put("CategoryType","Picked");
            map.put("ItemListActionName","PostList");
            map.put("PageIndex",x++); // 页码自增
            map.put("ParentCategoryId",0);
            map.put("TotalPostCount",1601);


            BlogTentacleUtils.scanPage(URLX, map,x);

        }
	}
}
