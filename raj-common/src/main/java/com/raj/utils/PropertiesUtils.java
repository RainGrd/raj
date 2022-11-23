package com.raj.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/15 12:30
 * FileName: PropertiesUtils
 * Description: 读取properties文件
 */
@Slf4j
public class PropertiesUtils {
    private Properties properties;

    private static PropertiesUtils propertiesUtils = new PropertiesUtils();

    /**
     * 私有创建，禁止直接创建
     */
    private PropertiesUtils() {
        properties = new Properties();
        InputStream resourceAsStream = this.getClass().getClassLoader().getResourceAsStream("app.properties");
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            log.info("加载app.properties报错,错误信息{}", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 获取单例
     *
     * @return
     */
    public static PropertiesUtils getInstance() {
        if (propertiesUtils == null) {
            propertiesUtils = new PropertiesUtils();
        }
        return propertiesUtils;
    }

    /**
     * 根据属性名读取值
     *
     * @param name 名称
     */
    public Object getProperty(String name) {
        return properties.getProperty(name);
    }


    /*************************************************************************/
    /*****************************读取属性，封装字段**************************/
    /*************************************************************************/

    /**
     * 是否开启调试模式
     */
    public Boolean isDebug() {
        return "true".equals(properties.getProperty("file.isDebug"));
    }

    public String getAttachmentServer() {
        return properties.getProperty("file.attachmentServer");
    }

    public String getAttachmentPath() {
        return properties.getProperty("file.attachmentPath");
    }

    public String getAttachmentGainPath() {
        return properties.getProperty("file.attachmentGainPath");
    }

    /**
     * 获取邮箱标题
     *
     * @return
     */
    public String getMailLoginTitle() {
        return properties.getProperty("mail.login.title");
    }

    /**
     * 获取邮箱账号
     *
     * @return
     */
    public String getMailAccount() {
        return properties.getProperty("mail.account");
    }

}
