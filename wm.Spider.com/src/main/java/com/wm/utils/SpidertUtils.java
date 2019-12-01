package com.wm.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wm.po.NewsPo;
import com.wm.service.RespondBoot;

/**
 * Created by CHENT
 * <a>http://news.china.com/history/today/0609/index.html#</a>
 * 抓取 中华网新闻 [历史上的今天] 的富文本内容
 */
public class SpidertUtils {


    public static List<NewsPo> scanToList(String initializeHtml) throws IOException {
        // 获取内容列表
        RespondBoot<List<String>> dataList = DownloadsUtils.ScanHTML( initializeHtml,"textlist", "class");
        Document doc = Jsoup.parse(dataList.getData().toString());
        //获取封面列表
        dataList =DownloadsUtils.ScanHTML(doc.html(),"imgbox", "class");
      
        StringBuilder context = new StringBuilder();  // 初始化容器        
        context.append(dataList.getData().toString()); // 将指定值存入
        doc = Jsoup.parse(context.toString());
        Elements elements = doc.select("div");
        int count=0;
        List<NewsPo> reslt  = new ArrayList<NewsPo>();
        for (Element element : elements) {
            count++;  // 计数器自增
            NewsPo item = new NewsPo();
            item.setSourceUrl(element.getElementsByTag("a").attr("href"));
            item.setCoverUrl(element.getElementsByTag("img").attr("src"));
            reslt.add(item);
        }
        return reslt;
    }

    public static void scanToItem(String initializeHtml,NewsPo item) throws IOException{
    	 // 获取内容列表
        RespondBoot<List<String>> dataList = DownloadsUtils.ScanHTML( initializeHtml,"viewtitle", "class");
        Document doc = Jsoup.parse(dataList.getData().toString());      
        Element h1 = doc.select("h1").first();
        if(null!=h1)
        {
        	item.setTitle(h1.text());
        }  
        
        dataList = DownloadsUtils.ScanHTML( initializeHtml,"viewinfo", "class");
        doc = Jsoup.parse(dataList.getData().toString());  
        Element span = doc.select("span").first();
        if(null!=span) {
        	Elements elements=span.select("i");
        	 for (Element element : elements) {
        		 if(element.text().contains("作者：")) {
        			 item.setAuthor(element.text().replace("作者：", ""));
        		 }
        		 if(element.text().contains("来源：")) {
        			 Element a = doc.select("a").first();
        			 if(null!=a) {
        				 item.setSource(a.text());
        				 item.setSourceUrl(a.attr("href"));
        			 }
        		 }
             }
        }
        dataList = DownloadsUtils.ScanHTML(initializeHtml,"pageleft", "class");        
        dataList = DownloadsUtils.ScanHTML(dataList.getData().toString(),"content", "class");         
    
         doc = Jsoup.parse(dataList.getData().toString());  
         Element div = doc.select("div").first();
         if(null!=div)
         { 
        	
        	 item.setContent(div.html()); // 将指定值存入     
         }
         
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