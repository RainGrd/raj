package com.raj.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.raj.entity.backend.Employee;
import com.raj.holder.EmployeeHolder;
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
        log.info("start insert fill ...");
        log.info("获取线程的员工对象:{}", employee);
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", employee.getId());
        metaObject.setValue("updateUser", employee.getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //获取线程的员工对象
        Employee employee = EmployeeHolder.getEmployee();
        log.info("start update fill ...");
        log.info("获取线程的员工对象:{}", employee);
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", employee.getId());
    }
}
