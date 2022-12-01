package com.raj.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.raj.common.JacksonObjectMapper;
import com.raj.interceptor.LoginInterceptor;
import com.raj.interceptor.RefreshTokenInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
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
//开启api
//@EnableOpenApi
public class MvcConfig implements WebMvcConfigurer {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 定义放行路径
        List<String> excludePathPatterns = new ArrayList<>();
        //backend
        excludePathPatterns.add("/backend/**");
        excludePathPatterns.add("/common/**");
        excludePathPatterns.add("/employee/login.do");
        excludePathPatterns.add("/favicon.ico");
        //front
        excludePathPatterns.add("/front/**");
        excludePathPatterns.add("/user/sendCode.do");
        excludePathPatterns.add("/user/login.do");
        //api
        excludePathPatterns.add("/doc.html");
        excludePathPatterns.add("/webjars/**");
        excludePathPatterns.add("/swagger-resources");
        excludePathPatterns.add("/v2/api-docs");
        // 定义拦截路径
        log.info("放行路径:{}", excludePathPatterns);
        registry.addInterceptor(new LoginInterceptor()).excludePathPatterns(excludePathPatterns).order(1);
        registry.addInterceptor(new RefreshTokenInterceptor(stringRedisTemplate)).order(0);
    }

    /**
     * MVC 消息转换器
     *
     * @param converters
     */
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
    /**
     * 加载静态资源映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        log.info("加载静态资源映射");
        registry.addResourceHandler("doc.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/backend/**").addResourceLocations("classpath:/backend/");
        registry.addResourceHandler("/front/**").addResourceLocations("classpath:/front/");
    }

}
