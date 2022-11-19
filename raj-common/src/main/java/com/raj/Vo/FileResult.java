package com.raj.Vo;

import lombok.Data;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/15 8:22
 * FileName: FileResult
 * Description: 文件传输类
 */
@Data
public class FileResult {
    /**
     * 要上传的文件名称
     */
    private String fileName;
    /**
     * 拓展名
     */
    private String extName;
    /**
     * 文件大小:字节，字符
     */
    private Long fileSize;
    /**
     * 文件存储在服务器的相对路径 例如:/image/9c0a301238644435bee6dbce57620ded.jpg
     */
    private String serverPath;
}
