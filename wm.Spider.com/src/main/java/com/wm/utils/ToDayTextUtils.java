package com.wm.utils;

import com.wm.po.SpiderFruit;
import com.wm.service.RespondBoot;
import com.wm.service.RespondCode;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by CHENT
 * <a>https://tool.lu/todayonhistory/</a>
 * 管理每天 : 历史上的今天的文本数据
 */

public class ToDayTextUtils {

    static org.slf4j.Logger logger = LoggerFactory.getLogger(ToDayTextUtils.class);

    /**
     * 解析历史上的今天的<li> 标签拿到全部数据
     *
     * @param expression    Jsoup 选择标签语法
     * @return
     */
    public static RespondBoot<String> scanToDayText(String expression) throws IOException {

        SpiderFruit initializeHtml = DownloadsUtils.getPageContent("https://tool.lu/todayonhistory/");

        RespondBoot<List<String>> DataList = DownloadsUtils.ScanHTML(initializeHtml.getEntitystr(), expression, "text");

        StringBuilder incident =  new StringBuilder();
        for (String data : DataList.getData()){
            logger.debug(data);

            StringBuilder keyWord =  new StringBuilder();
            StringBuilder search  =  new StringBuilder();
            keyWord.append(data.substring(0,data.length()-2));
            search.append(keyWord.toString().substring(keyWord.toString().indexOf(" "),keyWord.length())); // 取出关键字用于搜索

            // 将抓取到的 历史上的今天 添加标签
            incident.append("<li><a target=_blank href=https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd="+URLEncoder.encode(search.toString().trim(), "UTF-8")+"&rqlang=cn\">"+ keyWord.toString() +"</a></li>").toString();
        }

        return RespondBoot.createBySuccess(incident.toString());
    }


    public static RespondBoot<String> scanToDayDeepReading(String initializeHtml) throws IOException {

        StringBuilder context = new StringBuilder();   // 初始化容器
        StringBuilder context1 = new StringBuilder();  // 初始化容器
        StringBuilder context2 = new StringBuilder();  // 初始化容器
        StringBuilder context3 = new StringBuilder();  // 初始化容器
        StringBuilder context4 = new StringBuilder();  // 初始化容器

        RespondBoot<List<String>> DataList = DownloadsUtils.ScanHTML(initializeHtml, "info_left", "class");

        // 解析 info_left 结果 改变 A 标签的 href 属性 指向搜索引擎   https://www.baidu.com/s?ie=UTF-8&wd=
        Document doc = Jsoup.parse(DataList.getData().toString());

        // 解析多个 A 标签 改变它的 href 属性
        Elements elements = doc.select("h2 a");

        // 初始化
        context.append(DataList.getData().toString());

        int count = 0;

        // 将多个 A 标签 href 属性进行 偷梁换柱
        for (Element element : elements) {

            count++;  // 计数器自增

            // 偷梁
            String keyWord = element.text();

            // 换柱
            String ahref = element.attr("href");

            if (elements.size() - count == 0) {
                context4.append(context3.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
            }
            if (elements.size() - count == 1) {
                context3.append(context2.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
            }
            if (elements.size() - count == 2) {
                context2.append(context1.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
            }
            if (elements.size() - count == 3) {
                context1.append(context.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
            }

        }
        return RespondBoot.createBySuccess("<!--深度阅读-->"+context4.toString().substring(1,context4.toString().length()-1));
    }
}