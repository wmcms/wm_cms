package com.wilson.cms.utils;

import com.wilson.cms.config.Cms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
@Component
public  class IdUtils {

	@Autowired
	static URedis uRedis;
	@Autowired
    static Cms cms;
	public static  <T> Long NewID(Class<T> clizz){
//		Date date =new Date();
//		String formatStr = new SimpleDateFormat(UConstant.STR_DATE_TIME_FORMAT).format(date);
//		long resId = Long.parseLong(formatStr);
		String key =clizz.getName();
		Long resId = cms.initValue;
		System.out.println(uRedis.incr(key,1));
		return  resId+uRedis.incr(key,0);
	}

}
