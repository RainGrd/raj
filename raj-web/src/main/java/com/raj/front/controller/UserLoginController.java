package com.raj.front.controller;

import com.raj.Vo.Result;
import com.raj.entity.front.User;
import com.raj.front.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/19 16:16
 * FileName: LoginController
 * Description: 前台用户登录控制层
 */
@Controller
@Slf4j
public class UserLoginController {


    @Autowired
    private UserService userService;

   /* *//**
     * 发
     * @return
     *//*
    public Object getEmailCode(){

    }*/

    @PostMapping("/front/login.do")
    @ResponseBody
    public Object login(@RequestBody User user) {
        log.info("要登录的用户对象:{}", user);
        userService.login(user);
        return Result.success();
    }

}
