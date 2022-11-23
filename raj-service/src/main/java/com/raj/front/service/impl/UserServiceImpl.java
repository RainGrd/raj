package com.raj.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.extra.template.engine.freemarker.FreemarkerTemplate;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.Vo.Result;
import com.raj.constants.CommonEnum;
import com.raj.entity.backend.Employee;
import com.raj.entity.front.EmailModel;
import com.raj.entity.front.User;
import com.raj.exception.BaseRuntimeException;
import com.raj.front.service.UserService;
import com.raj.front.mapper.UserMapper;
import com.raj.holder.UserHolder;
import com.raj.utils.EmailUtils;
import com.raj.utils.UUIDUtils;
import com.raj.utils.ValidateCodeUtils;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lenovo
 * @description 针对表【t_user(用户信息)】的数据库操作Service实现
 * @createDate 2022-11-19 08:49:53
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    //    @Resource
//    private JavaMailSender mailSender;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Result login(Map<String, String> map) {
        //取出数据
        String code = map.get("code");
        String email = map.get("email");
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //从redis中获取验证码
        String redisCode = stringRedisTemplate.opsForValue().get(CommonEnum.FRONT_USER_EMAIL_CODE.getValue() + email);
        log.info("Redis中验证码:{}", redisCode);
        //如果Redis中验证码为空，则返回
        if (redisCode == null) {
            return Result.error("验证码已失效,请重新在获取");
        }
        //如果验证码不相等,返回错误提示
        if (!code.equals(redisCode)) {
            return Result.error("验证码错误！");
        }
        /*验证码正确,根据邮件查询用户对象*/
        userLambdaQueryWrapper.and(i -> i.eq(User::getEmail, email));
        User user = userMapper.selectOne(userLambdaQueryWrapper);
        log.info("查询出来的用户对象:{}", user);
        //判断用户是否存在
        if (user == null) {
            return Result.error("用户不存在！");
        }
        //如果头像设置默认头像
        if (user.getAvatar() == null) {
            //设置头像
            user.setAvatar("user.png");
        }
        //判断用户状态
        if (user.getStatus() == Integer.parseInt(CommonEnum.EMPLOYEE_AND_USER_ACCOUNT_STATUS_CLOSE.getValue())) {
            return Result.error("此账号已被禁用");
        }
        //使用UUID工具类的getUUID生成token(登录令牌)
        String token = UUIDUtils.getUUID();
        log.info("用户登录令牌:{}", token);
        // 拼接key
        String key = CommonEnum.FRONT_USER_EMAIL_TOKEN.getValue() + token;
        // 将用户对象存在Redis中
        stringRedisTemplate.opsForHash()
                //放置数据
                .putAll(key, BeanUtil.beanToMap(user, new HashMap<>(),
                        CopyOptions.create()
                                // 忽略null值
                                .setIgnoreNullValue(true)
                                // 设置字段值编辑器
                                .setFieldValueEditor((fileName, fileValue) -> fileValue.toString())));
        //设置登录过时时间 时间单位:毫秒
        stringRedisTemplate.expire(key, Long.parseLong(CommonEnum.EMPLOYEE_AND_USER_LOGIN_TIME.getValue()), TimeUnit.MILLISECONDS);
        //返回登录结果
        return Result.success(user, token);
    }

    @Override
    public void getEmailCode(User user) {
        //创建邮件封装对象
        EmailModel emailModel = new EmailModel();
        //收件人
        emailModel.setSendTo(user.getEmail());
        //设置标题
        emailModel.setTitle("邮箱登录验证码");
//        StringBuilder sb = new StringBuilder();
        //获取随机产生的验证码
        String code = ValidateCodeUtils.generateValidateCodeString(6);
        log.info("验证码-------------------------------:{}", code);
        // 设置内容
//        sb.append("<h1>尊敬的客户您好！</h1>").append("<p>你的验证码为").append(code).append("</p>");
//        emailModel.setText(sb.toString());
        emailModel.setText(code);
        // 拼接key key+用户登录的邮箱地址
        String key = CommonEnum.FRONT_USER_EMAIL_CODE.getValue() + user.getEmail();
        //将随机产生的验证码存放到Redis中
        stringRedisTemplate.opsForValue().set(key, code);
        //设置有效时间 时间单位:毫秒
        stringRedisTemplate.expire(key, Long.parseLong(CommonEnum.EMPLOYEE_AND_USER_LOGIN_TIME.getValue()), TimeUnit.MILLISECONDS);
        log.info("封装好的邮箱实体对象:{}", emailModel);
        //发送邮件
//        EmailUtils emailUtils = new EmailUtils();
//        emailUtils.sendHtmlEmail(emailModel);
//        EmailUtils.sendHtmlEmail(emailModel);
        EmailUtils.sendTemplateEngineEmail(emailModel);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }
}




