package com.wm.mapper;

import com.wm.service.RespondBoot;
import com.wm.service.RespondCode;
import com.wm.service.SpiderRunTimeException;
import com.wm.spider.TodaySpider;
import com.wm.utils.EmailUtils;
import com.wm.utils.FastIOUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by CHENT
 * <p>
 *  TodaySpiderDispatcher 对爬取结果进行校验,预警,日志记录等 !
 */
public class TodaySpiderDispatcher  {

    private static final Logger logger = LoggerFactory.getLogger(TodaySpiderDispatcher.class);

    public static void todayDispatcher(){

        try {

            logger.info(" 0: " + TodaySpiderDispatcher.class.getName() + " Start Initialise  ");

            // 1. 解析爬取结果 构建新页面完成 !
            RespondBoot<String> spiderResult = TodaySpider.todayNetworkSpider();
            // 2. 记录的新生成的页面路径日志 !
             logger.info(" 1: " + spiderResult.getMsg());
            // 3. 校验新页面是否生成成功 !
            RespondBoot<String> checkResult = FastIOUtils.checkIsFileNotNull(spiderResult.getData());
            logger.info(" 2: " + checkResult.getData());

        }catch (Throwable e){ // 监听捕获 Throwable的两个子分类Error和Exception

            // 判断异常制定不同的处理方式 :

            if (e instanceof SpiderRunTimeException){  // 校验 捕获的异常是否为自定义异常

                // 1. 如果为的 自定义异常(有HTTP状态码) 则进行 Jemail通知
                logger.error(e.getMessage());   //需要修改的地方 *  TODO
                EmailUtils.sendFastMail("自己的邮箱@qq.com", FastIOUtils.sysDate("yyyy年MM月dd日HH时 ")+ TodaySpiderDispatcher.class.getName()+" 运行时发生自定义异常 !",
                    "<body> ErrorCode: <a target=_blank href=https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd="+((SpiderRunTimeException) e).getErrorCode()+">"+((SpiderRunTimeException) e).getErrorCode()+"</a>"+"          ErrorMessage: TodaySpider "+((SpiderRunTimeException) e).getErrorMessage()+"</body>");

            }else{
                // 2. 如果为 运行时异常 则获取第一个异常名称  进行 Jemail通知
                logger.error(e.getMessage());  // TODO
                EmailUtils.sendFastMail("自己的邮箱@qq.com", FastIOUtils.sysDate("yyyy年MM月dd日HH时 ")+ TodaySpiderDispatcher.class.getName()+" 运行时发生未知异常 !","<body> Error Code:"+ RespondCode.RUNTIME_ERORR.getCode()+"          ErrorMessage: <a target=_blank href=https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&wd="+e.toString()+">"+e.toString()+"</a> </body>");

            };
        }
    }
}