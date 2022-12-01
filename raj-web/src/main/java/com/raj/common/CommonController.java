package com.raj.common;

import com.raj.Vo.FileResult;
import com.raj.Vo.Result;
import com.raj.utils.BootFileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/14 19:28
 * FileName: CommonController
 * Description: 公共的控制层
 */
@RestController
@Slf4j
@Api("公共控制")
@RequestMapping("/common")
public class CommonController {
    /**
     * 文件上传
     *
     * @param file 上传的文件对象
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadDishImage.do")
    @ApiModelProperty("上传文件接口")
    @ResponseBody
    public Result uploadDishImage(MultipartFile file) throws IOException {
        log.info("上传的文件名称:{}", file.getOriginalFilename());
        FileResult fileResult = BootFileUtils.uploadImage(file, "", "jpg,png,jpeg");
        log.info("上传完成后的文件名称:{}", fileResult.getFileName());
        // 储存上传的菜品图片文件 并封装数据
        return Result.success(fileResult.getServerPath());
    }

    /**
     * 文件下载
     *
     * @param name     要下载的文件名称
     * @param response 响应对象
     */

    @GetMapping("/download.do")
    public void download(@RequestParam String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("要下载的文件名称:{}", name);
        //使用文件工具类进行文件下载
        BootFileUtils.downloadFile(name, response);
    }
}
