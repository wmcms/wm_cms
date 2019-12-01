package com.wm.utils;

import java.util.Date;

import org.apache.commons.mail.SimpleEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wm.service.RespondBoot;

public class EmailUtils {

    private static final Logger logger = LoggerFactory.getLogger(EmailUtils.class);


    private static final String server = "smtp.163.com";          // 邮件服务器

    //需要修改的地方 *  TODO
    private static final String from = "自己的@163.com";          //发送者,显示的发件人名字

    private static final String user = "自己的@163.com";          //发送者邮箱地址

    private static final String password = "163邮箱授权码";       // 163邮箱授权码

    /**
     * 发送文本邮件
     *
     */
    public static void sendMail(String mailbox, String mailHeader, String mailContent) throws Exception{

        SimpleEmail mail = new SimpleEmail();
        // 设置邮箱服务器信息
        mail.setSslSmtpPort("25");
        mail.setHostName(server);
        // 设置密码验证器
        mail.setAuthentication(user, password);
        // 设置邮件发送者
        mail.setFrom(from);
        // 设置邮件接收者
        mail.addTo(user);   // 坑爹 163 邮箱设定! :发送别人的邮箱之前给自己发一封
        mail.addTo(mailbox);
        // 设置邮件编码
        mail.setCharset("UTF-8");
        // 设置邮件标题
        mail.setSubject(mailHeader);
        // 设置邮件内容
       // mail.setContent(mailContent,"text/html");
        // 设置邮件发送时间
        mail.setSentDate(new Date());
        // 发送邮件
        mail.send();
    }

    public static RespondBoot<String> sendFastMail(String mailbox, String mailHeader, String mailContent) {
        try {
            // 垃圾 163 邮箱!
            sendMail(mailbox, mailHeader, mailContent);

        } catch (Exception e) {
            logger.error("-1",e.getMessage());
        }
        return RespondBoot.createBySuccessMessage("QQ Eamil Send SUCCESS!");
    }

}