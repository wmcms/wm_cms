package com.wilson.cms.utils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.wilson.cms.exception.SpiderRunTimeException;
import com.wilson.cms.vo.RespondBoot;
import com.wilson.cms.vo.RespondCode;
import com.wilson.cms.vo.SpiderFruit;


public final class DownloadsUtils {

	/* private static String respond; */

    /**
     * @param Url
     * @return Content 页面下载方法
     */

    //<!--        Get请求        -->
    public static SpiderFruit getPageContent(String Url) throws IOException {

        // 创建可关闭的HttpClient实例对象(新版本才可以)相当于创建了一个模拟浏览器
        CloseableHttpClient httpclient = HttpClients.createDefault();

        // 一般爬虫请求都用Get，Get请求在HTTP请求协议里代表安全的查看:这个请求对象里可以添加http的请求头等
        HttpGet httpGet = new HttpGet(Url);

        // 设置超时断连
        RequestConfig configDL = RequestConfig.custom().setConnectTimeout(10000)// 设置连接时间超时10秒断连
            .setSocketTimeout(10000)// 设置读取时间超时10秒断连
            .build();


        // 添加请求配置
        httpGet.setConfig(configDL);

        // 设置Get请求头的 User-Agent (模拟代理浏览器信息)
        httpGet.setHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");

        // 用浏览器模拟对象httpClient，发送一个Get请求:可以通过这个响应对象获得很多http的响应信息
        CloseableHttpResponse respond = httpclient.execute(httpGet);

        //置空对象,提高速度
        httpGet = null;
        // 封装成一个爬虫结果类
        SpiderFruit SpiderFruit = new SpiderFruit();

        HttpEntity entity = respond.getEntity();
        SpiderFruit.setStatusCode(respond.getStatusLine().getStatusCode());
        SpiderFruit.setContentType(entity.getContentType().getValue());
        // 设置网页实体对象转换为字符串，并指定最终编码
        SpiderFruit.setEntitystr(EntityUtils.toString(entity, "utf-8"));

            // 关闭流资源
            httpclient.close();
            // 关闭流资源
            respond.close();

        // 抛出自定义异常
        if (SpiderFruit.getEntitystr().length() == 0 || SpiderFruit.getStatusCode() != 200 || Objects.isNull(SpiderFruit.getEntitystr())) {
            throw new SpiderRunTimeException(SpiderFruit.getStatusCode(),RespondCode.DOWNLOADS_ERORR.getDesc());
        }

            return SpiderFruit;

    }


    //<!--        Post请求        -->
    @SuppressWarnings("deprecation")
	public static SpiderFruit postPageContent(String Url, @SuppressWarnings("rawtypes") Map params) throws IOException {

        // 创建可关闭的HttpClient实例对象(新版本才可以)相当于创建了一个模拟浏览器
        CloseableHttpClient httpclient = HttpClients.createDefault();


        // 访问接口一般用 Post请求
        HttpPost httpPost = new HttpPost(Url);
        // 设置POST参数
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        for (@SuppressWarnings("rawtypes")
		Iterator iter = params.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String value = String.valueOf(params.get(name));
            nvps.add(new BasicNameValuePair(name, value));


        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));

        // 设置超时断连
        RequestConfig configDL = RequestConfig.custom().setConnectTimeout(10000)// 设置连接时间超时10秒断连
            .setSocketTimeout(10000)// 设置读取时间超时10秒断连
            .build();

        // 添加请求配置
        httpPost.setConfig(configDL);


        // 设置Get请求头的 User-Agent (模拟代理浏览器信息)
        httpPost.setHeader("User-Agent",
            "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");

        // 用浏览器模拟对象httpClient，发送一个Post请求:可以通过这个响应对象获得很多http的响应信息
        CloseableHttpResponse respond = httpclient.execute(httpPost);

        // 封装成一个爬虫结果类
        SpiderFruit SpiderFruit = new SpiderFruit();

        // 获得状态码
        SpiderFruit.setStatusCode(respond.getStatusLine().getStatusCode());

        // 获取返回的网页实体
        HttpEntity entity = respond.getEntity();
        if (entity != null) {
            // 设置响应内容类型
            SpiderFruit.setContentType(entity.getContentType().getValue());
        }

        // 设置网页实体对象转换为字符串，并指定最终编码
        SpiderFruit.setEntitystr(EntityUtils.toString(entity, "utf-8"));

        if (Objects.isNull(SpiderFruit.getEntitystr())){ // 状态码不一致 返回null
             throw new NullPointerException("");
        }
        // 关闭流资源
        httpclient.close();
        // 关闭流资源
        respond.close();
        return SpiderFruit;
    }

    /**
     * 根据 原始页面 和 JSOUP语法 获取数据返回集合
     *
     * @param initializeHtml        初始化的页面
     * @param expression            JSOUP语法
     * @return
     * @throws IOException
     */
    public static RespondBoot<List<String>> ScanHTML(String initializeHtml, String expression, String Type) throws IOException {

        List<String> DataList = new ArrayList<String>();

        Document Page = Jsoup.parse(initializeHtml);

        if ("text".equals(Type)) {
            Elements select = Page.select(expression);
            for (Element element : select) {
                DataList.add(element.text());
            }
        } else if ("id".equals(Type)) {
            DataList.add(Page.getElementById(expression).toString());
        } else if ("class".equals(Type)) {
            DataList.add(Page.getElementsByClass(expression).toString());
        } else if ("first".equals(Type)) {
            DataList.add(Page.select(expression).first().toString());
        }

        return RespondBoot.createBySuccess(DataList);
    }

}
