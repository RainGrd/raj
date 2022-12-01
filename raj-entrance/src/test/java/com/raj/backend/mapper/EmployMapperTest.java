package com.raj.backend.mapper;

import com.raj.entity.backend.Employee;
import com.raj.mapper.backend.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 10:07
 * FileName: EmployMapperTest
 * Description:
 */
@SpringBootTest
public class EmployMapperTest {


    @Resource
    private EmployeeMapper employeeMapper;


    @Test
    void getAll() {
        List<Employee> employees = employeeMapper.selectList(null);
        System.out.println("employees = " + employees);
    }


    @Test
    void testAdd() {
        Employee employee = new Employee();
        employee.setName("test012");
        employee.setUsername(employee.getName());
        employee.setPassword("e10adc3949ba59abbe56e057f20f883e");
        employee.setPhone("13812312312");
        employee.setStatus(1);
        employee.setIdNumber("110101199001010047");
        employee.setSex("1");
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(1L);
        employee.setUpdateUser(1L);
        employeeMapper.insert(employee);
    }
}
