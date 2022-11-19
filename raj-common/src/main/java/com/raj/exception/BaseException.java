package com.raj.exception;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/10 15:04
 * FileName: BaseException
 * Description: 自定义全局异常类
 */
public class BaseException extends Exception{
    private static final long serialVersionUID = -6561063628566489377L;

    public BaseException(String message) {
        super(message);
    }
}
