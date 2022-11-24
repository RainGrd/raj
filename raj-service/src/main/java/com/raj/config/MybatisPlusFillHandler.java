package com.raj.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.raj.entity.backend.Employee;
import com.raj.entity.front.User;
import com.raj.holder.EmployeeHolder;
import com.raj.holder.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/14 10:31
 * FileName: MybatisPlusFillHandler
 * Description: mybatis-plus自动填充实现类
 */
@Configuration
@Slf4j
public class MybatisPlusFillHandler implements MetaObjectHandler {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void insertFill(MetaObject metaObject) {
        //获取线程的员工对象
        Employee employee = EmployeeHolder.getEmployee();
        // 获取线程的用户对象
        User user = UserHolder.getUser();
        //获取id值
        Long id = employee != null ? employee.getId() : user.getId();
        log.info("获取到的id值:{}", id);
        log.info("start insert fill ...");
        log.info("获取线程的员工对象:{}", employee);
        log.info("获取线程的用户对象:{}", user);
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", id);
        metaObject.setValue("updateUser", id);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取线程的员工对象
        Employee employee = EmployeeHolder.getEmployee();
        // 获取线程的用户对象
        User user = UserHolder.getUser();
        //获取id值
        Long id = employee != null ? employee.getId() : user.getId();
        log.info("获取到的id值:{}", id);
        log.info("start update fill ...");
        log.info("获取线程的员工对象:{}", employee);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", id);
    }
}
