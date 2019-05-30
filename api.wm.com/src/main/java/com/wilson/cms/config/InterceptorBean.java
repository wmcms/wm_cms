package com.wilson.cms.config;


import com.wilson.cms.filter.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

@Configuration
@Component
public class InterceptorBean extends WebMvcConfigurationSupport {

    private static final String FAVICON_URL = "/favicon.ico";

    /**
     * 发现如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/").addResourceLocations("/**");
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }


    /**
     * 配置servlet处理
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
      //  registry.addInterceptor(securityInterceptor()).excludePathPatterns("/static/*")
          //      .excludePathPatterns("/error").addPathPatterns("/**");

        registry.addInterceptor(authTokenInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(FAVICON_URL)
                .excludePathPatterns("/static/**");
        super.addInterceptors(registry);
    }


    //将拦截器作为bean写入配置中
    @Bean
    public LoginInterceptor authTokenInterceptor() {
        return new LoginInterceptor();
    }
}
