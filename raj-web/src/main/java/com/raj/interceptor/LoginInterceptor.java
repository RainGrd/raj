package com.raj.interceptor;

import cn.hutool.json.JSONUtil;
import com.raj.Vo.Result;
import com.raj.holder.EmployeeHolder;
import com.raj.entity.backend.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 12:49
 * FileName: LoginInterceptor
 * Description: 登录拦截器
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 取出当前线程的员工对象
        Employee employee = EmployeeHolder.getEmployee();
        log.info("当前线程的员工对象:{}", employee);
        if (employee == null) {
            log.info("拦截到请求：{}", request.getRequestURI());
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            //回写错误信息
            response.getWriter().write(JSONUtil.toJsonStr(Result.error("NOTLOGIN")));
            return false;
        }
        /*if (id==null) {
            return false;
        }*/
        //放行
        return true;
    }
}
