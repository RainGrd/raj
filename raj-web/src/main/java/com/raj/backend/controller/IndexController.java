package com.raj.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/27 14:45
 * FileName: IndexController
 * Description: 跳转首页的Controller
 */
@Controller
public class IndexController {
    /**
     * 跳转到员工首页方法
     * @return
     */
    @GetMapping("/backend/page/toIndex.do")
//    @ResponseBody
    public String index() {
        System.out.println("index.do");
//        return "backend/page/index";
        return "backend/index.html";
    }




    /**
     * 跳转登录页面方法
     * @return
     */
    @RequestMapping("/backend/page/login/toLogin.do")
    public String toLogin() {
        System.out.println("toLogin.do");
        return "/backend/page/login/login.html";
    }


}
