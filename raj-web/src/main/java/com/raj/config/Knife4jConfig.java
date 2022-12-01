package com.raj.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Classname Knife4jConfig
 * @Description TODO
 * @Version 1.0.0
 * @Date 2022/11/30 17:03
 * @Author RainGrd
 */
@Configuration
@Slf4j
//开启swagger
@EnableSwagger2
//开启knife4j
@EnableKnife4j
public class Knife4jConfig {
    /**
     * 定义创建的文档类型
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).groupName("all").select().apis(RequestHandlerSelectors.basePackage("com.raj.controller")).paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("瑞吉外卖").contact(new Contact("RainGrd", "", "3408396469@qq.com")).version("1.2").description("瑞吉外卖接口文档").build();
    }
}
