package com.raj.config;

import com.raj.common.JacksonObjectMapper;
import com.raj.interceptor.LoginInterceptor;
import com.raj.interceptor.RefreshTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.xml.ws.Action;
import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 12:42
 * FileName: MvcConfig
 * Description: mvc配置类
 */
@Configuration
@Slf4j
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 定义放行路径
        List<String> excludePathPatterns = new ArrayList<>();
        excludePathPatterns.add("/backend/api/**");
        excludePathPatterns.add("/backend/images/**");
        excludePathPatterns.add("/backend/js/**");
        excludePathPatterns.add("/backend/plugins/**");
        excludePathPatterns.add("/backend/styles/**");
        excludePathPatterns.add("/backend/favicon.ico");
        excludePathPatterns.add("/backend/login/**");
        excludePathPatterns.add("/common/**");
        excludePathPatterns.add("/backend/page/**");
        excludePathPatterns.add("/favicon.ico");
        excludePathPatterns.add("/backend/index.html");
        excludePathPatterns.add("/front/**");
        // 定义拦截路径
        List<String> addPathPatterns = new ArrayList<>();
        log.info("放行路径:{}", excludePathPatterns.toString());
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns(addPathPatterns).excludePathPatterns(excludePathPatterns).order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).order(0);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("加载消息转换器...");
        //创建消息转换器对象
        MappingJackson2HttpMessageConverter messageConverter = new MappingJackson2HttpMessageConverter();
        //设置对象转换器，底层使用Jackson将Java对象转为json
        messageConverter.setObjectMapper(new JacksonObjectMapper());
        //将上面的消息转换器对象追加到mvc框架的转换器集合中
        converters.add(0, messageConverter);
    }
}
