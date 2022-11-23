package com.raj.utils;

import cn.hutool.extra.template.engine.thymeleaf.ThymeleafEngine;
import com.raj.entity.front.EmailModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;

/**
 * @Classname EmailUtils
 * @Description 邮箱工具类
 * @Version 1.0.0
 * @Date 2022/11/22 10:34
 * @Author RainGrd
 */
@Slf4j
@Component
public class EmailUtils {
    @Resource
    private JavaMailSender mailSender;

    private static JavaMailSender javaMailSender;

    private static String from;

    @Resource
    private TemplateEngine template;

    private static TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @PostConstruct
    public void init() {
        javaMailSender = mailSender;
        from = fromAddress;
        templateEngine = template;
    }

    /**
     * 发送html文本的邮件
     *
     * @param emailModel 邮件实体类对象
     */
    public static void sendHtmlEmail(EmailModel emailModel) {
        //发送邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            log.info("发件人:{}", from);
            helper.setFrom(from);
            //收件人
            log.info("收件人:{}", emailModel.getSendTo());
            helper.setTo(emailModel.getSendTo());
            //设置标题
            log.info("邮件标题,{}", emailModel.getTitle());
            helper.setSubject(emailModel.getTitle());
            log.info("邮件内容,{}", emailModel.getText());
            helper.setText(emailModel.getText(), true);
        } catch (Exception e) {
            //打印错误信息
            log.error(e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
        //发送邮件
        javaMailSender.send(mimeMessage);
        log.info("邮件已经发送成功");
    }

    /**
     * 发送模板邮件
     *
     * @param emailModel 邮件实体类对象
     */
    public static void sendTemplateEngineEmail(EmailModel emailModel) {
        //创建邮件对象
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            log.info("发件人:{}", from);
            helper.setFrom(from);
            //收件人
            log.info("收件人:{}", emailModel.getSendTo());
            helper.setTo(emailModel.getSendTo());
            //设置标题
            log.info("邮件标题:{}", emailModel.getTitle());
            helper.setSubject(emailModel.getTitle());
            //创建邮箱正文对象
            Context context = new Context();
            //获取邮箱正文
            context.setVariable("code", Arrays.asList(emailModel.getText().split("")));
            log.info("邮件内容:{}", emailModel.getText());
            String emailContent = templateEngine.process("email", context);
            helper.setText(emailContent, true);
        } catch (Exception e) {
            //打印错误信息
            log.error(e.getMessage());
            throw new RuntimeException("邮件发送失败");
        }
        //发送邮件
        javaMailSender.send(mimeMessage);
        log.info("邮件已经发送成功");
    }
}
