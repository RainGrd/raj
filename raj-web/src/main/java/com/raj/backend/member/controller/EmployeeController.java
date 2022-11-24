package com.raj.backend.member.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.backend.service.EmployeeService;
import com.raj.entity.backend.Employee;
import com.raj.exception.BaseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/9 16:15
 * FileName: EmployeeController
 * Description: 员工控制层
 */
@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {


    @Resource
    private EmployeeService employeeService;

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @PostMapping("/insertEmployee.do")
    public Object insertEmployee(@RequestBody Employee employee) throws BaseException {
        System.out.println("employee = " + employee);
        int i = employeeService.saveEmployee(employee);
        if (i <= 0) {
            return Result.error("添加失败");
        }
        return Result.success();
    }

    /**
     * 员工分页方法
     *
     * @param pageSize 页数
     * @param page     页码
     * @param name     员工名称
     * @return java.lang.Object
     */
    @GetMapping("/queryEmployeeForPage.do")
    public Object queryEmployeeForPage(int pageSize, int page, String name) {
        //输出日志
        log.info("pageSize:{},page:{},name:{}", pageSize, page, name);
        System.out.println("page:" + page);
        Page<Employee> employeePage = employeeService.queryEmployeeListForPage(pageSize, page, name);
        log.info("输出查询的分页数据:{}", employeePage.getRecords().toString());
        return Result.success(employeePage);
    }

    /**
     * 用于修改员工对象的请求方法
     *
     * @param employee
     * @return
     */
    @PutMapping("/updateEmployee.do")
    public Object updateEmployee(@RequestBody Employee employee) {
        log.info("要修改的员工对象:{}", employee);
        int modifyEmployee = employeeService.modifyEmployee(employee);
        if (modifyEmployee <= 0) {
            return Result.error("修改失败");
        }
        return Result.success();
    }

    /**
     * 根据id查询单个员工对象
     *
     * @param id
     * @return
     */
    @GetMapping("/selectById.do/{id}")
    public Object selectById(@PathVariable Long id) {
        return Result.success(employeeService.queryEmployeeById(id));
    }
}
