package com.raj.backend.login.controller;

import com.raj.backend.service.EmployeeService;
import com.raj.entity.backend.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 9:16
 * FileName: EmployeeController
 * Description: 员工Controller
 */
@Slf4j
@Controller
public class EmployeeLoginController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录方法
     *
     * @return java.lang.Object
     */
    @PostMapping("/backend/login/login.do")
    @ResponseBody
    public Object login(@RequestBody Employee e) {
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
     * @return java.lang.Object
     */
    @RequestMapping("/backend/login/loginOut.do")
    @ResponseBody
    public Object loginOut(HttpServletRequest request) {
        String token = request.getHeader("authorization");
        // 调用员工service层删除本机存在Redis中的员工信息 返回退出登录结果
        return employeeService.loginOut(token);
    }
    
}
