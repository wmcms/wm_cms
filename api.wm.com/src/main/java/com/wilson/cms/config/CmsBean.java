package com.wilson.cms.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.github.pagehelper.PageHelper;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Properties;

/**
 * @ClassName CmsBean
 * @Description Cms系统配置
 * @Author wilson
 * @Date 2019/5/29 20:52
 * @Version 1.0
 **/
@Configuration
public class CmsBean {

	@Bean
	public DefaultKaptcha getKaptchaBean() {
		DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
		Properties properties = new Properties();
		// 边框样式
		properties.setProperty("kaptcha.border", "yes");
		properties.setProperty("kaptcha.border.color", "105,179,90");
		// 验证码颜色
		properties.setProperty("kaptcha.textproducer.font.color", "red");
		// 图片长宽
		properties.setProperty("kaptcha.image.width", "130");
		properties.setProperty("kaptcha.image.height", "50");
		// 字符大小
		properties.setProperty("kaptcha.textproducer.font.size", "40");
		properties.setProperty("kaptcha.session.key", "code");
		// 设置字体个数
		properties.setProperty("kaptcha.textproducer.char.length", "4");
		properties.setProperty("kaptcha.textproducer.font.names", "宋体,楷体,微软雅黑");
		Config config = new Config(properties);
		defaultKaptcha.setConfig(config);
		return defaultKaptcha;
	}

	/**
	 * 分页插件处理
	 * 
	 * @return
	 */
	@Bean
	public PageHelper pageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("offsetAsPageNum", "true");
		properties.setProperty("rowBoundsWithCount", "true");
		properties.setProperty("reasonable", "true");
		properties.setProperty("dialect", "mysql"); // 配置mysql数据库的方言
		pageHelper.setProperties(properties);
		return pageHelper;
	}

	/**
	 * Redis实现类配置
	 * 
	 * @param factory
	 * @return
	 */
	@Bean
	@SuppressWarnings("all")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(factory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
		// key采用String的序列化方式
		template.setKeySerializer(stringRedisSerializer);
		// hash的key也采用String的序列化方式
		template.setHashKeySerializer(stringRedisSerializer);
		// value序列化方式采用jackson
		template.setValueSerializer(jackson2JsonRedisSerializer);
		// hash的value序列化方式采用jackson
		template.setHashValueSerializer(jackson2JsonRedisSerializer);
		template.afterPropertiesSet();
		return template;
	}

	@Bean
	public Converter<String, Timestamp> stringDateConvert() {
		return new Converter<String, Timestamp>() {
			@Override
			public Timestamp convert(String source) {
				// yyyy-MM-dd HH:mm:ss日期字符串转换为Date类型
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Timestamp date = null;
				try {
					date = Timestamp.valueOf(source);
				} catch (Exception e) {
					// yyyy-MM-dd日期字符串转换为Date类型
					sdf = new SimpleDateFormat("yyyy-MM-dd");
					try {
						date = Timestamp.valueOf(source);
					} catch (Exception e1) {
						throw e1;
					}
				}
				return date;
			}

			@Override
			public JavaType getInputType(TypeFactory typeFactory) {
				return null;
			}

			@Override
			public JavaType getOutputType(TypeFactory typeFactory) {
				return null;
			}
		};
	}

}
