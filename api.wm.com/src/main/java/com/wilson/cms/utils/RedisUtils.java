package com.wilson.cms.utils;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
* MD5 工具类-建议添油加醋的对入参 str 改造一下
* @author Administrator
*
*/
@Component
public class RedisUtils {

	@Autowired
	 RedisTemplate<String, Object> redisTemplate;
	
	

	// 维护一个本类的静态变量
	static RedisUtils redisUtils;

	@PostConstruct
	public void init() {
		redisUtils = this;
		redisUtils.redisTemplate = this.redisTemplate;
	}
	/**
	 * 设置有效时间
	 *
	 * @param key Redis键
	 * @param timeout 超时时间
	 * @return true=设置成功；false=设置失败
	 */
	public static boolean expire(final String key, final long timeout) {

		return expire(key, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 设置有效时间
	 *
	 * @param key Redis键
	 * @param timeout 超时时间
	 * @param unit 时间单位
	 * @return true=设置成功；false=设置失败
	 */
	public static boolean expire(final String key, final long timeout, final TimeUnit unit) {

		Boolean ret = redisUtils.redisTemplate.expire(key, timeout, unit);
		return ret != null && ret;
	}

	/**
	 * 删除单个key
	 *
	 * @param key 键
	 * @return true=删除成功；false=删除失败
	 */
	public static boolean del(final String key) {

		Boolean ret = redisUtils.redisTemplate.delete(key);
		return ret != null && ret;
	}


	/**
	 * 存入普通对象
	 *
	 * @param key Redis键
	 * @param value 值
	 */
	public static void set(final String key, final Object value) {

		redisUtils.redisTemplate.opsForValue().set(key, value, 1, TimeUnit.MINUTES);
	}

	// 存储普通对象操作

	/**
	 * 存入普通对象
	 *
	 * @param key 键
	 * @param value 值
	 * @param timeout 有效期，单位秒
	 */
	public static void set(final String key, final Object value, final long timeout) {

		redisUtils.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	/**
	 * 获取普通对象
	 *
	 * @param key 键
	 * @return 对象
	 */
	public static Object get(final String key) {

		Object res= redisUtils.redisTemplate.opsForValue().get(key);
		
		return res;
	}

	/**
	 * 获取普通对象
	 *
	 * @param key 键
	 * @return 对象
	 */
	public static Long incr(final String key) {
		Long increment = redisUtils.redisTemplate.opsForValue().increment(key);
		expire(key,1);
		return  increment;
	}


}
