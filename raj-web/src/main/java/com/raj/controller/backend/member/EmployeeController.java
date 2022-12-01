package com.raj.controller.backend.member;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.service.backend.EmployeeService;
import com.raj.entity.backend.Employee;
import com.raj.exception.BaseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/9 16:15
 * FileName: EmployeeController
 * Description: 员工控制层
 */
@Api("员工相关接口")
@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {
    @Resource
    private EmployeeService employeeService;

    /**
     * 员工登录方法
     *
     * @return java.lang.Result
     */
    @ApiOperation("员工登录接口")
    @PostMapping("/login.do")
    public Result login(@RequestBody Employee e) {
        log.info("要登录的员工:{}", e);
        String username = e.getUsername();
        String password = e.getPassword();
        //进行MD5的加密处理
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        //返回登录结果
        return employeeService.login(username, password);
    }

    /**
     * 退出登录
     *
     * @return java.lang.Result
     */
    @ApiOperation("退出登录接口")
    @RequestMapping("/loginOut.do")
    public Result loginOut(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        // 调用员工service层删除本机存在Redis中的员工信息 返回退出登录结果
        return employeeService.loginOut(token);
    }

    /**
     * 添加员工
     *
     * @param employee
     * @return
     */
    @ApiOperation("添加员工接口")
    @PostMapping("/insertEmployee.do")
    public Result insertEmployee(@RequestBody Employee employee) throws BaseException {
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
     * @return java.lang.Result
     */
    @ApiOperation("员工分页方法接口")
    @GetMapping("/queryEmployeeForPage.do")
    public Result queryEmployeeForPage(int pageSize, int page, String name) {
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
    @ApiOperation("用于修改员工对象的请求方法接口")
    @PutMapping("/updateEmployee.do")
    public Result updateEmployee(@RequestBody Employee employee) {
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
    @ApiOperation("根据id查询单个员工对象接口")
    @GetMapping("/selectById.do/{id}")
    public Result selectById(@PathVariable Long id) {
        return Result.success(employeeService.queryEmployeeById(id));
    }
}
