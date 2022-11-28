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
     * 员工和用户的登录时间 30分钟 时间单位:毫秒
     */
    EMPLOYEE_AND_USER_LOGIN_TIME("1800000"),
    /**
     * 邮箱验证码有效时间为5分钟, 时间单位:毫秒
     */
    MAIL_CODE("300000"),
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
    FRONT_USER_EMAIL_TOKEN("front:user:email:token:"),

    /**
     * 外卖地址的默认值 1:设置为默认地址
     */
    ADDRESS_BOOK_IS_DEFAULT_YES("1"),
    /**
     * 外卖地址的默认值 0:不设置为默认地址
     */
    ADDRESS_BOOK_IS_DEFAULT_NO("0"),
    /**
     * 订单状态:代付款
     */
    ORDERS_STATUS_PAYMENT("1"),
    /**
     * 订单状态:待派送
     */
    ORDERS_STATUS_DELIVERED("2"),
    /**
     * 订单状态:已派送
     */
    ORDERS_STATUS_DISPATCH("3"),
    /**
     * 订单状态:已完成
     */
    ORDERS_STATUS_COMPLETED("4"),
    /**
     * 订单状态:已取消
     */
    ORDERS_STATUS_CLOSE("5"),
    /**
     * 根据分类查询菜品
     */
    FRONT_DISH_CATEGORY_LIST("front:dish:category:list:"),
    /**
     * 缓存的公共的超时时间,60分钟
     */
    CACHE_COMMON_TIME_UNIT("60");
    private final String value;


    CommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
