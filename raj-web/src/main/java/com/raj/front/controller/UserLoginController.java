package com.raj.front.controller;

import com.raj.Vo.Result;
import com.raj.entity.front.User;
import com.raj.front.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    /*
     * 向邮箱发送验证码
     * @return
     */
    @PostMapping("/front/login/sendCode.do")
    @ResponseBody
    public Object sendCode(@RequestBody User user) {
        userService.getEmailCode(user);
        return Result.success("验证码已经发送成功！");
    }

    /**
     * 用户登录请求
     *
     * @param map
     * @return
     */
    @PostMapping("/front/login/login.do")
    @ResponseBody
    public Object login(@RequestBody Map<String, String> map) {
        log.info("要登录的用户对象:{}", map);
        return userService.login(map);
    }

}
