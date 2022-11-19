package com.raj.exception;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/10 15:05
 * FileName: BaseRuntimeException
 * Description: 自定义全局运行异常类
 */
public class BaseRuntimeException extends RuntimeException{


    private static final long serialVersionUID = -1305402911801445463L;

    public BaseRuntimeException(String message) {
        super(message);
    }
}
