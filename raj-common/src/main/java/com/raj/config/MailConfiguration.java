package com.raj.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * @Classname MailConfiguration
 * @Description 邮件配置类
 * @Version 1.0.0
 * @Date 2022/11/22 12:06
 * @Author RainGrd
 */
//@Configuration
@Slf4j
public class MailConfiguration {
    /**
     * 配置Java邮件对象
     *
     * @return
     */
//    @Bean
    public JavaMailSenderImpl JavaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//         邮箱域名
        mailSender.setHost("smtp.qq.com");
//         邮箱用户名
        mailSender.setUsername("3408396469@qq.com");
//         邮箱密码
        mailSender.setPassword("ehxpiojrhuktdbhg");
//         邮箱字符集
        mailSender.setDefaultEncoding("UTF-8");
        return mailSender;
    }
}
