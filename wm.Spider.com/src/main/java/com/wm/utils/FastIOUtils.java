package com.wm.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.wm.service.RespondBoot;
import com.wm.service.RespondCode;
import com.wm.service.SpiderRunTimeException;

/**
 * Created by CHENT
 *
 * 封装了一层
 */
public class FastIOUtils {


    /**
     * 根据日期格式获取当前时间字符串
     *
     * @param   FormatRule   当前时间格式化规则
     * @return  sysdate
     */
    public static String sysDate(String FormatRule) {
        SimpleDateFormat df = new SimpleDateFormat(FormatRule);   //设置日期格式 列: "yyyy-MM-dd HH:mm:ss"
        return df.format(new Date());      // new Date()为获取当前系统时间，也可使用当前时间戳
    }

    /**
     * 校验正式服或者开发服 文件是否存在
     *
     *
     * @return
     */
    public static RespondBoot<String> checkIsFileNotNull (String path) {

        File isFile = new File(path);

        if (!isFile.exists()){ // 如果不存在抛出异常
            throw new SpiderRunTimeException(RespondCode.CONSTRUCTION_ERORR.getCode(),RespondCode.CONSTRUCTION_ERORR.getDesc());
        }

        return RespondBoot.createBySuccess(path + " CheckOut SUCCESS !");
    }

    /**
     * 获取一个32 位的UUID
     *
     * @return UUID
     */
    public static String randomUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
        return uuid.replaceAll("-", "");
    }
    /**
     * 创建空白文件并写入内容
     * @param  url  创建文件的路径
     * @param  Data 创建文件后文件中的内容
     * @throws IOException
     */
    public static void createNewFile(String url , String Data) throws IOException {

        File file = new File(url);

        try {
            org.apache.commons.io.FileUtils.writeStringToFile(file, Data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}