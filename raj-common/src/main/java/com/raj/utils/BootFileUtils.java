package com.raj.utils;

import com.raj.Vo.FileResult;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/14 20:24
 * FileName: BootFileUtils
 * Description: boot文件工具类
 */
@Slf4j
public class BootFileUtils {
    /**
     * 属性配置
     */
    private static final PropertiesUtils propertiesUtils = PropertiesUtils.getInstance();

    /**
     * 方法描述:上传文件
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @param isImage       上传的是否是图片，如果是就会进行图片压缩；如果不希望图片被压缩，则传false，让其以文件的形式来保存
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public static FileResult uploadFile(MultipartFile multipartFile, String childFile, String extension, Boolean isImage) throws IOException {
        //判断非空
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new IOException("上传的文件不存在");
        }
        // 源文件名称
        String fileName = multipartFile.getOriginalFilename();
        //文件后缀名
        String suffix = FilenameUtils.getExtension(fileName);
        if (StringUtils.isEmpty(suffix)) {
            throw new RuntimeException("文件类型未定义不能上传...");
        }
        //判断文件的后缀名是否符合规则
        isContains(extension, suffix);
        //创建目标文件的名称,规则请看destPath方法
        String genFileName = genFileName(childFile, suffix);
        // 文件的实际路径
        String serverPath = getServerPath(genFileName);
        // 创建文件
        log.info("服务器的路径:{}", serverPath);
        File destFile = createFile(serverPath);
        log.info("创建的文件名称:{}", destFile.getName());
        // 保存文件
        multipartFile.transferTo(destFile);
        //拼装返回的数据
        FileResult fileResult = new FileResult();
        // 如果文件是图片的话
        if (BooleanUtils.isTrue(isImage)) {
            // 进行图片处理
            String toFilePath = thumbnails(serverPath, childFile, suffix);
            log.info("图片处理后的相对路径:{}", toFilePath);
            // 得到处理后的图片文件对象
            File file = new File(getServerPath(toFilePath));
            // 得到压缩后的文件后缀名
            String extExtName = FilenameUtils.getExtension(toFilePath);
            // 源文件=源文件名.压缩前的后缀名
            String extFileName = FilenameUtils.getBaseName(fileName) + "." + FilenameUtils.getExtension(toFilePath);
            log.info("上传的图片名称:{}", extFileName);
            // 封装数据
            fileResult.setFileSize(file.length());
            fileResult.setServerPath(toFilePath);
            fileResult.setFileName(extFileName);
            fileResult.setExtName(extExtName);
        } else {
            // 封装数据
            fileResult.setFileSize(multipartFile.getSize());
            fileResult.setServerPath(genFileName);
            fileResult.setFileName(fileName);
            fileResult.setExtName(serverPath);
        }
        //获取上传的文件的文件名
        return fileResult;
    }

    /**
     * 判断文件是否存在
     *
     * @param fileSource 文件
     */
    public static boolean fileIsExist(File fileSource) {
        return fileSource.exists();
    }

    /**
     * 处理要上传的图片
     *
     * @param serverPath 图片的绝对路径
     * @param childFile  子文件夹
     * @param suffix     后缀名
     * @return java.lang.String
     */
    private static String thumbnails(String serverPath, String childFile, String suffix) throws IOException {
        // 要压缩后的图片文件名称
        String toFileName = genFileName(childFile, suffix);
        //忽略大小写,判断文件后缀名是否等于png
        if ("png".equalsIgnoreCase(suffix)) {
            // 删除以前的后缀名
            String removeExtensionFilePath = FilenameUtils.removeExtension(toFileName);
            // Thumbnails用于压缩png文件，会越压越大,所以得将文件输出的后缀名改为.jpg
            Thumbnails
                    // 部署文件
                    .of(serverPath)
                    // 图片的缩放比例
                    .scale(1f)
                    // 设置图片的压缩比例
                    .outputQuality(0.5f)
                    // 文件输出的后缀名
                    .outputFormat("jpg")
                    // 设置图片位置
                    .toFile(getServerPath(removeExtensionFilePath));
            toFileName = removeExtensionFilePath + ".jpg";
        } else {
            // 不等于直接压缩图片
            Thumbnails
                    // 部署文件
                    .of(serverPath)
                    // 图片的缩放比例
                    .scale(1f)
                    // 设置图片的压缩比例
                    .outputQuality(0.5f)
                    // 设置图片位置
                    .toFile(getServerPath(toFileName));
        }
        // 删除被压缩的文件
        FileUtils.forceDelete(new File(serverPath));
        // 返回图片地址
        return toFileName;
    }

    /**
     * 根据文件的绝对路径创建一个文件对象
     *
     * @param serverPath 文件的绝对路径
     * @return java.io.File
     */
    private static File createFile(String serverPath) throws IOException {
        // 获取文件的完整目录
        String fileDir = FilenameUtils.getFullPath(serverPath);
        log.info("文件的完整目录:{}", fileDir);
        // 判断目录是否存在，不存在就创建一个目录
        File file = new File(fileDir);
        if (!file.isDirectory()) {
            // 创建失败返回null
            if (!file.mkdirs()) {
                throw new IOException("文件目录创建失败...");
            }
        }
        // 判断那个文件是否存在,不存在就创建
        file = new File(serverPath);
        if (!file.exists()) {
            if (!file.createNewFile()) {
                throw new IOException("目标文件创建失败...");
            }
        }
        return file;
    }

    /**
     * 生成文件存在服务器的实际路径
     *
     * @param destPath 文件的相对路径
     * @return java.lang.String
     */
    private static String getServerPath(String destPath) {
        return FilenameUtils.separatorsToSystem(propertiesUtils.getAttachmentGainPath() + destPath);
    }

    /**
     * 生成文件文件名
     *
     * @param childFile 子目录
     * @param suffix    后缀名
     * @return java.lang.String
     */
    private static String genFileName(String childFile, String suffix) {
        //生成文件名规则:子目录/年月日_随机数.后缀名
/*        final double d = Math.random();
        final int i = (int) (d * 1000);
        return childFile + "/" + DateUtil.formatDate(new Date()) + "-" + i + "." + suffix;*/
        //生成文件名规则:UUID
        return UUIDUtils.getUUID() + "." + suffix;
    }

    /**
     * 方法描述:判断extension中是否存在suffix
     *
     * @param extension 使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip
     * @param suffix    文件的后缀名
     */

    private static void isContains(String extension, String suffix) {
        if (StringUtils.isNotEmpty(extension)) {
            //切割文件拓展名
            String[] suffixS = StringUtils.split(extension, ",");
            log.info("切割完成文件拓展名数组:{}", suffixS);
            if (ArrayUtils.isNotEmpty(suffixS)) {
                assert suffixS != null;
                //使用断言判断文件拓展名数组
                List<String> exList = Arrays.asList(suffixS);
                log.info("exList:{}", exList);
                //判断
                if (!exList.contains(suffix)) {
                    throw new RuntimeException("上传文件的类型只能是:" + extension);
                }
            } else {
                // 判断文件的后缀名是否为extension
                if (!extension.equalsIgnoreCase(suffix)) {
                    throw new RuntimeException("上传文件的类型只能是：" + extension);
                }
            }
        }
    }

    /**
     * 文件下载
     *
     * @param fileName 要下载文件名称
     * @param response 响应对象
     */
    public static void downloadFile(String fileName, HttpServletResponse response) throws IOException {
        File file = new File(propertiesUtils.getAttachmentGainPath() + fileName);
        log.info("文件全路径:{}",propertiesUtils.getAttachmentGainPath() + fileName);
        boolean flag = fileIsExist(file);
        if (flag) {
            InputStream ips = null;
            OutputStream os = null;
            try {
                ips = Files.newInputStream(file.toPath());
                ByteArrayOutputStream outStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = ips.read(buffer)) != -1) {
                    outStream.write(buffer, 0, len);
                }
                ips.close();
                byte[] data = outStream.toByteArray();
                // 重置响应对象
                response.reset();
                // 缓存对象
                response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
                response.setContentType("application/octet-stream;image/jpeg;image/jpg;");
                os = response.getOutputStream();
                os.write(data);
                os.flush();
                os.close();
            } catch (IOException e) {
                // 抛出业务异常
                throw new RuntimeException("文件下载失败！");
            } finally {
                //关闭流
                log.info("关闭流...");
                if (ips != null) {
                    ips.close();
                }
                if (os != null) {
                    os.close();
                }
            }
        } else {
            throw new IOException("下载失败，源文件不存在！");
        }
    }

    /**
     * 方法描述：上传文件
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public static FileResult uploadFile(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return uploadFile(multipartFile, childFile, extension, false);
    }

    /**
     * 方法描述：上传图片.
     * 创建时间：2018-10-19 13:09:19
     *
     * @param multipartFile 上传的文件对象，必传
     * @param childFile     上传的父目录，为空直接上传到指定目录 （会在指定的目录下新建该目录，例如：/user/1023）
     * @param extension     允许上传的文件后缀名，为空不限定上传的文件类型 （使用逗号隔开的字符串，精确匹配例如：txt,jpg,png,zip）
     * @return the file result
     * @throws IOException 异常信息应返回
     */
    public static FileResult uploadImage(MultipartFile multipartFile, String childFile, String extension) throws IOException {
        return uploadFile(multipartFile, childFile, extension, true);
    }
}
