package com.wm.service;

/**
 * Created by CHENT
 *  final常量类
 */
public class Const {
    public static final String WIN_TODAY_HTMLPATH="D://ALocal_tool//Depots//src//main//resources//"; // win 和 Linux 上盘符不一样
    public static final String Linux_TODAY_HTMLPATH="/data/wwwroot/default/tocsin/"; // win 和 Linux 上盘符不一样

    // 在interface中定义的常量默认被JavaC隐式转换为public static final类型的
    public  interface Role{

    }

    //  历史上的今天 样式和 头部HTML
    public static final String STYLE_AND_HEADER = "<html xmlns=\"http://www.w3.org/1999/xhtml\"><head><link rel=\"stylesheet\" href=\"http://www.china.com/zh_cn/plugin/all/newspush/css/style.css\">\n" +
        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
        "    <meta name=\"author\" content=\"cwj 14270\">\n" +
        "    <title>历史上的今天</title>\n" +
        "    <link href=\"http://news.china.com//zh_cn/history/today/css/style.css\" rel=\"stylesheet\" media=\"all\">\n" +
        "</head>\n" +
        "<body>\n" +
        "<!-- /etc/ztHeader.shtml Start -->\n" +
        "\n" +
        "\n" +
        "<style>\n" +
        "    #ztHeader {background:url(http://news.china.com/etc/images/ztHeader.png); color:#1b1b1b; font:12px/1.5 simsun; width:950px; margin:0 auto 5px;}\n" +
        "    #ztHeader img {border:0;}\n" +
        "    #ztHeader ul {border:1px solid #cbcbcb; height:33px; overflow:hidden; padding:0; margin:0; list-style-type:none;}\n" +
        "    #ztHeader li {padding:0; margin:0; list-style-type:none;}\n" +
        "    #ztHeader a {color:#333; margin:0 8px; text-decoration:none;}\n" +
        "    #ztHeader a:hover {color:#f00; text-decoration:none;}\n" +
        "    #ztHeader .ztLogo {float:left; padding-top:4px; text-align:center; width:130px;}\n" +
        "    #ztHeader .ztChnl {float:left; padding:10px 15px 0 0; text-align:right; width:800px;}\n" +
        "</style>\n" +
        "\n" +
        "\n" +
        "<!-- /etc/ztHeader.shtml End -->\n" +
        "<!-- /zh_cn/etc/channelheadtop1000.shtml Start -->\n" +
        "<style>\n" +
        "    .chanNav{ background:#f7f7f7; border-bottom:1px solid #d9d9d9; height:30px;font-size:12px; min-width:1000px;}\n" +
        "    .chanNavCon {width:1000px; margin:0 auto; *zoom:1; letter-spacing:0;}\n" +
        "    .chanNavCon:after { display:block; clear:both; content:\"\"; visibility:hidden; height:0;}\n" +
        "    .chanNav span.left{ float:left; padding-top:8px;}\n" +
        "    .chanNav span.left a{padding:0 1px;}\n" +
        "    .chanNav span.right{ float:right; padding-top:5px;}\n" +
        "    .chanNav span.right a{ margin-left:10px;}\n" +
        "    .chanNav span.right a.cnLogin{background:#d03c4d; color:#fff; font-weight:bold; margin-left:10px; padding:3px 4px;}\n" +
        "    .chanNav span.right a.cnLogin:hover{background:#6e0007;}\n" +
        "    .chanNav a{ display:inline-block;font-size:12px; color:#000; text-decoration:none;}\n" +
        "    .chanNav a:hover{font-size:12px; color:#b31515; text-decoration:underline;}\n" +
        "    .chanTopNickname { line-height:20px;}\n" +
        "    .cht-01 {text-align:center; margin:5px auto; width:1000px;}\n" +
        "    .adbroker_com{ margin:0 auto;}\n" +
        "</style>\n" +
        "\n";



    public static final String CENTER_AND_AUTHOR = "<div class=\"clear\"></div>\n" +
        "\n" +
        "<div id=\"center\" class=\"wrapper\">\n" +
        "\n" +
        "   \n" +
        "    <div class=\"wrap_text\">\n" +
        "        <img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n";


    public static final String AUTHOR_AND_READ="<img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n" +
        "    </div>\n" +
        "\n" +
        "    <div class=\"wrap_text\">\n" +
        "        <img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n" +
        "        <div class=\"info\">";

    public static final String READ_AND_INCIDENT= "    </div>\n" +
        "\n" +
        "        <img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n" +
        "    </div>\n" +
        "    <!-- 大事记 -->\n" +
        "    <div class=\"wrap_text\">\n" +
        "        <img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n" +
        "        <div class=\"info\">\n" +
        "            <img src=\"http://news.china.com//zh_cn/history/today/img/title_djs.jpg\">\n <ul>";




    public static final String SITE_AND_TAIL = "\n" +
        "        </ul></div>\n" +
        "        <img src=\"http://news.china.com//zh_cn/history/today/img/arrow1.jpg\">\n" +
        "    </div>\n" +
        "</div>\n" +
        "\n" +
        "<!-- /zh_cn/etc/channelfoot20081110.shtml Start -->\n" +
        "<style type=\"http://news.china.com//zh_cn/history/today/text/css\">\n" +
        "    <!--\n" +
        "    #pageFooter {\n" +
        "        margin:0 auto;\n" +
        "        padding:1px 0;\n" +
        "        line-height:21px;\n" +
        "        color:#333;\n" +
        "        font-size:12px;\n" +
        "        font-family:\"宋体\";\n" +
        "    }\n" +
        "    #pageFooter a {\n" +
        "        color:#333;\n" +
        "        font-size:12px;\n" +
        "        font-family:\"宋体\";\n" +
        "    }\n" +
        "    -->\n" +
        "</style>\n" +
        "\n" +
        "\n" +
        "<center>\n" +
        "    <p>关于站点</p>\n" +
        "    <p class=\"info\"> 这是一个 HDMBS 基于SpringBoot 实践的一个 Java爬虫 Demo，开发过程中给我的技术带来了少许精进，也希望能给大家带来帮助！！！<a href=\"https://gitee.com/HDMBS/JavaSpiderDemo\" target=\"_blank\" title=\"码云地址 :\"><br>码云地址 : https://gitee.com/HDMBS/JavaSpiderDemo</a>\n" +
        "    </p>\n" +
        "</center> </div></body></html>";


}