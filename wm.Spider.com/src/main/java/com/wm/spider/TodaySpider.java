package com.wm.spider;

import com.wm.mapper.TodaySpiderDispatcher;
import com.wm.po.SpiderFruit;
import com.wm.service.Const;
import com.wm.service.RespondBoot;
import com.wm.utils.DownloadsUtils;
import com.wm.utils.FastIOUtils;
import com.wm.utils.ToDayRichTextUtils;
import com.wm.utils.ToDayTextUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by CHENT
 */
public class TodaySpider {

    private static final Logger logger = LoggerFactory.getLogger(TodaySpiderDispatcher.class);

    public static RespondBoot<String>  todayNetworkSpider () throws Exception {

        
        StringBuilder toDayHTML = new StringBuilder(); // 初始化 HTML 模板

        // 定时抓取 原始页面
        SpiderFruit initializeHtml = DownloadsUtils.getPageContent("http://news.china.com/history/today/" + FastIOUtils.sysDate("MMdd") + "/index.html");

        toDayHTML.append(Const.STYLE_AND_HEADER); // 1. 添加 头和样式 模板

        // 历史上的今天 头部富文本内容抓取
        RespondBoot<String> headerData = ToDayRichTextUtils.scanToDayRichTextHeader(initializeHtml.getEntitystr());
        toDayHTML.append(headerData.getData().toString());  // 2. 追加新抓取头部富文本
        toDayHTML.append(Const.CENTER_AND_AUTHOR); // 2.1 添加中心和编者的话模板
        logger.debug(headerData.getData().toString());

        // 历史上的今天 编者的话抓取
        RespondBoot<String> introduceData = ToDayRichTextUtils.scanToDayRichTextIntroduce(initializeHtml.getEntitystr());
        toDayHTML.append(introduceData.getData().toString());  // 3. 追加新抓取编者的话
        toDayHTML.append(Const.AUTHOR_AND_READ);   // 3.1 添加编者的话和深入阅读模板
        logger.debug(introduceData.getData().toString());

        // 历史上的今天 深度阅读抓取
        RespondBoot<String> deepReadingData = ToDayTextUtils.scanToDayDeepReading(initializeHtml.getEntitystr());
        toDayHTML.append(deepReadingData.getData().toString());   // 4. 追加新抓取深度阅读
        logger.debug(deepReadingData.getData().toString());

        // 历史上的今天 老照片抓取
        RespondBoot<String> oldPicData = ToDayRichTextUtils.scanToDayRichTextOldPic(initializeHtml.getEntitystr());
        toDayHTML.append(oldPicData.getData().toString());    // 5. 追加新抓取老照片
        toDayHTML.append(Const.READ_AND_INCIDENT);   // 5.1 添加深入阅读和大事记模板
        logger.debug(oldPicData.getData().toString());

        // 历史上的今天 大事记抓取
        RespondBoot<String> incidentHtml = ToDayTextUtils.scanToDayText("#tohlis li");

        logger.debug(incidentHtml.getData().toString());
        toDayHTML.append(incidentHtml.getData().toString());   // 6. 追加大事件html

        toDayHTML.append(Const.SITE_AND_TAIL); // 7. 添加 站点介绍


        // 改为自己的文件生成目录 TODO
        String  history = Const.WIN_TODAY_HTMLPATH+ FastIOUtils.sysDate("yyyyMMdd")+".html";
        String  today = Const.WIN_TODAY_HTMLPATH+"today.html";

        FastIOUtils.createNewFile(history,toDayHTML.toString());  // 生成历史上今天记录页面

        FastIOUtils.createNewFile(today,toDayHTML.toString());    // 生成历史上今天页面

        return  RespondBoot.createBySuccess(" Construction "+history+" Complete !",history);
    }

}