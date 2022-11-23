package com.raj;

import com.raj.utils.MusicPlay;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(value = {"com.raj.backend", "com.raj.common", "com.raj.utils", "com.raj.config", "com.raj.front"})
// 开始事务注解支持
@EnableTransactionManagement
@MapperScan({"com.raj.backend.mapper", "com.raj.front.mapper"})
public class RajEntranceApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(RajEntranceApplication.class, args);
//        MusicPlay.play("D:\\lenovo\\Desktop\\周杰伦-《稻香》 MP3音乐免费下载 (2).mp3");
    }

}
