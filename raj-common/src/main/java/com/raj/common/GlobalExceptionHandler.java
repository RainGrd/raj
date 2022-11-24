package com.raj.common;

import com.raj.Vo.Result;
import com.raj.exception.BaseException;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/10 14:59
 * FileName: GlobalExceptionHandler
 * Description: 自定义全局异常处理器
 */
@RestControllerAdvice(annotations = {Controller.class, RestController.class})
@Slf4j
public class GlobalExceptionHandler {
    /**
     * 用于处理系统异常的异常处理方法 状态码为500
     *
     * @return
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception ex) {
        log.error("异常错误信息:{}", ex.getMessage());
        return Result.error("系统繁忙,正在维护中！");
    }

    /**
     * 用于处理自定义异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(BaseRuntimeException.class)
    public Result runtimeExceptionHandler(BaseRuntimeException ex) {
        log.error("异常错误信息:{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 用于处理sql异常的异常处理方法 状态码为500
     *
     * @return
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public Result SqlexceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error("异常错误信息:{}", ex.getMessage());
        /*用于判断插入数据到数据库
         * 如果数据库里面的数据已存在则返回错误提示
         * */
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return Result.error(msg);
        }
        return Result.error("系统繁忙,正在维护中！");
    }

}
