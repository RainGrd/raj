package com.raj;

import com.raj.utils.MusicPlay;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(value = {"com.raj.service", "com.raj.entity", "com.raj.controller", "com.raj.common", "com.raj.utils", "com.raj.config", "com.raj.mapper"})
// 开始事务注解支持
@EnableTransactionManagement
@MapperScan({"com.raj.mapper"})
public class RajEntranceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RajEntranceApplication.class, args);
    }

}
