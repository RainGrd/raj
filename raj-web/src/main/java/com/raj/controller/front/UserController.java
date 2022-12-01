package com.raj.controller.front;

import com.raj.Vo.Result;
import com.raj.constants.CommonEnum;
import com.raj.entity.front.User;
import com.raj.service.front.UserService;
import com.raj.holder.UserHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/19 16:16
 * FileName: LoginController
 * Description: 前台用户控制层
 */
@RestController
@Api("用户相关接口")
@Slf4j
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /*
     * 向邮箱发送验证码
     * @return
     */
    @ApiOperation("向邮箱发送验证码接口")
    @PostMapping("/sendCode.do")
    public Result sendCode(@RequestBody User user) {
        userService.getEmailCode(user);
        return Result.success("验证码已经发送成功！");
    }

    /**
     * 用户登录请求
     *
     * @param map
     * @return
     */
    @ApiOperation("用户登录请求接口")
    @PostMapping("/login.do")
    public Result login(@RequestBody Map<String, String> map) {
        log.info("要登录的用户对象:{}", map);
        return userService.login(map);
    }

    /**
     * 用户退出登录方法
     *
     * @param request
     * @return
     */
    @ApiOperation("用户退出登录方法接口")
    @PostMapping("/loginOut.do")
    public Result loginOut(HttpServletRequest request) {
        log.info("用户退出登录...");
        //删除存放在本地线程池的用户
        UserHolder.removeUser();
        //删除存放在Redis中的用户
        stringRedisTemplate.delete(CommonEnum.FRONT_USER_EMAIL_TOKEN.getValue() + request.getHeader("authorization"));
        //返回结果
        return Result.success();
    }

}
