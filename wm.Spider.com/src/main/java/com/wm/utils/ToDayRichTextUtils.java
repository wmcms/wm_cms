package com.wm.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wm.service.RespondBoot;

import java.io.IOException;
import java.util.List;

/**
 * Created by CHENT
 * <a>http://news.china.com/history/today/0609/index.html#</a>
 * 抓取 中华网新闻 [历史上的今天] 的富文本内容
 */
public class ToDayRichTextUtils {


    /**
     * // 历史上的今天 头部富文本内容抓取
     *
     * @return RespondBoot<String>
     * @throws IOException
     */
    public static RespondBoot<String> scanToDayRichTextHeader(String initializeHtml) throws IOException {

    	System.out.println(initializeHtml);
    	
        // 获取 top1  标签数据
        RespondBoot<List<String>> dataList = DownloadsUtils.ScanHTML( initializeHtml,"header", "id");

        // 解析 top1 结果 改变 A 标签的 href 属性 指向搜索引擎   https://www.baidu.com/s?ie=UTF-8&wd=
        Document doc = Jsoup.parse(dataList.getData().toString());

        // 查找第一个 h4 元素 作为百度搜索 wd 参数值
        String headerh4 = doc.select("h4").first().text();

        // 查找第一个 a 元素 替换他的 href 属性
        String headera = doc.select("a").first().attr("href");

        // 偷梁换柱
        String headerResult =  dataList.getData().toString().replace(headera,"https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd="+headerh4+"&rqlang=cn").substring(1, dataList.getData().toString().length()-1);

        return RespondBoot.createBySuccess(headerResult);
    }

    /**
     * 历史上的今天 编者的话抓取
     *
     * @return RespondBoot<String>
     * @throws IOException
     */
    public static RespondBoot<String> scanToDayRichTextIntroduce(String initializeHtml) throws IOException {

        RespondBoot<List<String>> dataList = DownloadsUtils.ScanHTML(initializeHtml, "#center .wrap_text .info", "first");

        return RespondBoot.createBySuccess("<!--编者的话-->"+dataList.getData().toString().substring(1,dataList.getData().toString().length()-1));
    }


    /**
     * 历史上的今天 老照片抓取
     *
     * @return RespondBoot<String>
     * @throws IOException
     */
    public static RespondBoot<String> scanToDayRichTextOldPic(String initializeHtml) throws IOException {

        StringBuilder context = new StringBuilder();  // 初始化容器
        StringBuilder context1 = new StringBuilder();  // 初始化容器
        StringBuilder context2 = new StringBuilder();  // 初始化容器

        RespondBoot<List<String>> dataList = DownloadsUtils.ScanHTML(initializeHtml, "info_ri", "class");

        context.append(dataList.getData().toString()); // 将指定值存入

        // 解析 info_ri 结果 改变 A 标签的 href 属性 指向搜索引擎   https://www.baidu.com/s?ie=UTF-8&wd=
        Document doc = Jsoup.parse(context.toString());

        // 解析多个 A 标签 改变它的 href 属性
        Elements elements = doc.select(".info_ri .photoAlbum_item .photoAlbum_item_txt a");

        int count = 0;

        // 将多个 A 标签 href 属性进行 偷梁换柱
        for (Element element : elements) {

            count++;  // 计数器自增

            // 偷梁
            String keyWord = element.text();

            // 换柱
            String ahref = element.attr("href");

                if (elements.size() - count == 1){
                context1.append(context.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
                }
                if (elements.size() - count == 0){
                    context2.append(context1.toString().replaceAll(ahref, "https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd=" + keyWord + "&rqlang=cn"));
            }
        }

        return RespondBoot.createBySuccess("<!-- 老照片 -->"+context2.toString().substring(1,context2.toString().length()-1));
    }


}