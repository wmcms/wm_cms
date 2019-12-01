package com.wm.blog;

import com.wm.po.SpiderFruit;
import com.wm.utils.DownloadsUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

/**
 * Created by CHENT
 *
 * 主要用于抓取博客园精华板块的用户头像
 */
 public  class BlogTentacleUtils {

    // SpringBoot 默认集成LogBack
     static org.slf4j.Logger logger = LoggerFactory.getLogger(BlogTentacleUtils.class);

    /**
     * 解析Page分页 拿到最大页码值
     * @param URL           需要解析的Page路径
     * @param expression    Jsoup 选择标签语法
     * @param countx        拿到那条记录
     * @return
     */

    public static String Aherf_Page_Max(String URL,String expression, int countx) throws IOException {

        SpiderFruit SpiderFruit = DownloadsUtils.getPageContent(URL);

        Document PageMax = Jsoup.parse(SpiderFruit.getEntitystr());

        Elements select = PageMax.select(expression);
        String Value = null; // 获取文本值
        int count = 0;

        for (Element element : select) {
            count++;
            if (count == countx){
                Value =  element.text(); // 获取DOM元素包裹的文本
            }
        }
        return  Value;
    }

    /**
     * 解析 Page分页 拿到需要的数据
     *
     * @param URLX         解析地址
     * @param map          POST请求参数
     * @throws IOException
     *
     *    // 设置 POST 参数
    Map<String, Object> map = new HashMap<>();
    map.put("CategoryId",-2);
    map.put("CategoryType","Picked");
    map.put("ItemListActionName","PostList");
    map.put("PageIndex",x++); // 页码自增
    map.put("ParentCategoryId",0);
    map.put("TotalPostCount",1601);

     *
     */
    public static void scanPage(String URLX , Map map,int x) throws IOException {

            SpiderFruit SpiderFruitX = DownloadsUtils.postPageContent(URLX,map);


            //爬取网页标签的长度
            int length = SpiderFruitX.getEntitystr().length();

            //状态码
            Integer statusCode = SpiderFruitX.getStatusCode();


            if(length !=0  && statusCode.equals(200) ) {
                logger.info("\n\r"+"爬取页面 "+x+"|"+URLX+" | SUCCESS成功"+"\n\r");
            }else {
                logger.error("\n\r"+"爬取页面 "+x+"|"+URLX+"| ERROR错误"+"\n\r"+"状态码:"+statusCode+"\n\r"+"网页长度:"+length+"\n\r");
            }


            // 用Jsoup将网页解析为文档对象
            Document doc = Jsoup.parse(SpiderFruitX.getEntitystr());


            Elements Titleimgs = doc.select("img[src$=.png]");


            for (Element element : Titleimgs) {//查找扩展名为Png结尾的DOM元素
                //并用attr()解析element获取src属性的值
                logger.info(element.attr("src").toString().substring(2));
            }

            logger.info("\n\r"+"-----------------解析 "+x+"|"+ URLX +" 页面完成--------------"+"\n\r");
    }

    // 通过Html层叠标签后找到目标数据:以下是播客园首页博客用户头像模板:并不能解析DOM元素的属性值
    private static void titleimg_DOM(Document doc) {
        Elements Titleimgs = doc.select("img[src$=.png]");
        for (Element element : Titleimgs) {// 查找扩展名为Png结尾的DOM元素
            // 获取src的地址
            System.out.println(element.attr("src"));
        }
    }


    // 通过Html层叠标签后找到目标数据:以下是播客园title模板
    private static void titles_Demo(Document doc) {
        // 文档对象.select() :括号里面写选择器语法 | ID用 #id值 | class用 .class | 没有id和class的用标签名

        Elements titles = doc.select("#post_list .post_item .post_item_body  h3 a");
        for (Element element : titles) {
            System.out.println(element.text());
        }
    }

    // 如果是用文本显示:则标签获取匹配的第一个Class所有元素并以文本展示 //如果是用Html显示:则符合的标签全部显示 String text =
    private static void Class_Demo(Document doc) {
        String text = doc.getElementsByClass("post_item").text();
        System.out.println(text);
    }

    private static void Demo_ID(Document doc) {
        // 使用标签ID获取元素获得单个元素
        String elementById = doc.getElementById("shicineirong").text();
        System.out.println(elementById);
    }

    private static void Tag_Demo(Document doc) {
        // 获取任意标签返回多个元素
        // [获取HTML标签是title的所有元素]Demo1 获取文章 https://zhuanlan.zhihu.com/p/30974799
        Elements h2s = doc.getElementsByTag("h2"); // 根据HTML_Tag名称来获得多个标签字段； Elements
        Elements blockquotes = doc.getElementsByTag("blockquote");
        for (int i = 0; i < h2s.size(); i++) {
            // 获取索引上的元素并以文本展示 System.out.println(h2s.get(i).text());
            System.out.println(blockquotes.get(i).text());
            System.out.println();

        }
    }


}