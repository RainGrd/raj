package com.raj.entity.front;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 用户信息
 *
 * @TableName t_user
 */
@Data
@Component
@ApiModel("用户信息")
public class User implements Serializable {

    private static final long serialVersionUID = -3282017022187007050L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;
    /**
     * 姓名
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @ApiModelProperty("姓名")
    @Length(max = 50, message = "编码长度不能超过50")
    @TableField("name")
    private String name;
    /**
     * 手机号
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @ApiModelProperty("手机号")
    @Length(max = 100, message = "编码长度不能超过100")
    @TableField("phone")
    private String phone;
    /**
     * 性别 0:男 1:女
     */
    @ApiModelProperty("性别 0:男 1:女")
    @TableField("sex")
    private Integer sex;
    /**
     * 身份证号
     */
    @Size(max = 18, message = "编码长度不能超过18")
    @ApiModelProperty("身份证号")
    @Length(max = 18, message = "编码长度不能超过18")
    @TableField("id_number")
    private String idNumber;
    /**
     * 头像
     */
    @Size(max = 500, message = "编码长度不能超过500")
    @ApiModelProperty("头像")
    @Length(max = 500, message = "编码长度不能超过500")
    @TableField("avatar")
    private String avatar;
    /**
     * 状态 0:禁用，1:正常
     */
    @NotNull(message = "[状态 0:禁用，1:正常]不能为空")
    @ApiModelProperty("状态 0:禁用，1:正常")
    @TableField("status")
    private Integer status;
    /**
     * 用户邮箱
     */
    @NotBlank(message = "[用户邮箱]不能为空")
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("用户邮箱")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField("email")
    private String email;
}
