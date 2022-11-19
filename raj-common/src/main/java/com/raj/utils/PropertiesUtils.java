package com.raj.utils;

import javafx.scene.media.AudioClip;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
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
            e.printStackTrace();
            log.info("加载app.properties报错,错误信息{}", e.getMessage());
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
     * 是否调试模式
     */
    public Boolean isDebug() {
        return "true".equals(properties.getProperty("isDebug"));
    }

    public String getAttachmentServer() {
        return properties.getProperty("attachmentServer");
    }

    public String getAttachmentPath() {
        return properties.getProperty("attachmentPath");
    }

    public String getAttachmentGainPath() {
        return properties.getProperty("attachmentGainPath");
    }

}
