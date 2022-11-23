package com.raj.constants;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/10/26 9:35
 * FileName: CommonEnum
 * Description: 公共的常量类
 */
public enum CommonEnum {
    YMD("yyyy-MM-dd"),
    YMD_HMS("yyyy-MM-dd HH:mm:ss"),
    HMS("HH:mm:ss"),
    /**
     * 响应数据编码 1成功，0失败
     */
    RESULT_CODE_SUCCESS("1"),
    RESULT_CODE_FAIL("0"),
    /**
     * 员工登录标识
     */
    EMPLOYEE_LOGIN_TOKEN("employee:login:token:"),
    /**
     * 员工和用户的登录时间 时间单位:毫秒
     */
    EMPLOYEE_AND_USER_LOGIN_TIME("1800000"),
    /**
     * 员工账号状态:1是启用
     */
    EMPLOYEE_AND_USER_ACCOUNT_STATUS_START("1"),
    /**
     * 员工和用户账号状态:0是关闭
     */
    EMPLOYEE_AND_USER_ACCOUNT_STATUS_CLOSE("0"),
    /**
     * 逻辑删除字段常量值:0:未删除
     */
    IS_DELETED_NO("0"),
    /**
     * 逻辑删除字段常量值:1:已删除
     */
    IS_DELETED_YES("1"),
    /**
     * 套餐和菜品的状态 1:启售
     */
    SETMEAL_DISH_STATUS_YES("1"),
    /**
     * 套餐和菜品的状态 0:停售
     */
    SETMEAL_DISH_STATUS_NO("0"),
    /**
     * 用户邮箱验证码
     */
    FRONT_USER_EMAIL_CODE("front:user:email:code:"),
    /**
     * 用户登录令牌
     */
    FRONT_USER_EMAIL_TOKEN("front:user:email:token:");

    private final String value;


    CommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
