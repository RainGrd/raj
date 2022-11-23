package com.raj.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @Classname EmailModel
 * @Description 邮件实体类
 * @Version 1.0.0
 * @Date 2022/11/22 8:42
 * @Author RainGrd
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class EmailModel implements Serializable {
    private static final long serialVersionUID = -3179776407731284999L;
    /**
     * 收件人
     */
    private String sendTo;
    /**
     * 邮件主题
     */
    private String title;
    /**
     * 邮件内容
     */
    private String text;
    /**
     * 附件路径
     */
    private String filePath;

}
