package com.raj.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/9 20:00
 * FileName: CommonUtil
 * Description: 公共工具类
 */
public class WebUtil {

    /**
     * 用于取出requset的Token值
     *
     * @param request
     * @return
     */
    public static String getHeadToken(HttpServletRequest request) {
        return request.getHeader("authorization");
    }
}
