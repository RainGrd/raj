package com.raj.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.entity.backend.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 10:43
 * FileName: EmployeeServiceTest
 * Description:
 */
@SpringBootTest
public class EmployeeServiceTest {

    @Resource
    private EmployeeService employeeService;


    @Test
    void testLogin() {
        System.out.println(employeeService.login("admin", "123456"));
    }


    @Test
    void testAddEmployee() {
        Employee employee = new Employee();
        employee.setName("admin");
        employee.setUsername("user");
        employee.setPassword("drg1224605");
        employee.setPhone("15873461176");
        employee.setSex("0");
        employee.setIdNumber("430481200512240371");
        employee.setStatus(1);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(1L);
        employee.setUpdateUser(1L);
        employeeService.saveEmployee(employee);
    }


    @Test
    void queryEmployeeListForPageTest() {
        Page<Employee> employeePage = employeeService.queryEmployeeListForPage(10, 1, "rain");
        System.out.println(employeePage.getRecords());
    }


    @Test
    void queryEmployeeByIdTest() {
        System.out.println(employeeService.queryEmployeeById(1L));
    }
}
