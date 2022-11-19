package com.raj.interceptor;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.StrUtil;
import com.raj.constants.CommonEnum;
import com.raj.holder.EmployeeHolder;
import com.raj.entity.backend.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 12:46
 * FileName: RefreshTokenInterceptor
 * Description: 用于刷新登录令牌的拦截器
 */
@Slf4j
public class RefreshTokenInterceptor implements HandlerInterceptor {
    /**
     * 拦截器在spring容器创建完成之前完成，所以不能是自动注入，使用构造函数通过mvc配置类进行注入
     */
    private StringRedisTemplate stringRedisTemplate;

    public RefreshTokenInterceptor(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("authorization");
        log.info("登录令牌:{}", token);
        // 判断登录令牌是否为空
        if (StrUtil.isBlank(token)) {
            // null返回true
            return true;
        }
        String key = CommonEnum.EMPLOYEE_LOGIN_TOKEN.getValue() + token;
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(key);
        //判断员工是否存在
        log.info("Redis储存中的员工:{}", entries);
        if (MapUtil.isEmpty(entries)) {
            //null返回true
            return true;
        }
        //使用hutool的工具类将map转换为bean对象
        Employee employee = BeanUtil.fillBeanWithMap(entries, new Employee(), false);
        // 员工存在则将员工对象存放在线程对象
        EmployeeHolder.setEmployee(employee);
        // 刷新有效期
        stringRedisTemplate.expire(key, Long.parseLong(CommonEnum.EMPLOYEE_LOGIN_TIME.getValue()), TimeUnit.MINUTES);
        return true;
    }
}
