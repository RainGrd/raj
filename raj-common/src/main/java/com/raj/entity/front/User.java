package com.raj.entity.front;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * 用户信息
 *
 * @TableName t_user
 */
@Data
public class User implements Serializable {
    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 姓名
     */
    @TableField(value = "name")
    private String name;

    /**
     * 手机号
     */
    @TableField(value = "phone")
    private String phone;

    /**
     * 性别 0:男 1:女
     */
    @TableField(value = "sex")
    private Integer sex;

    /**
     * 身份证号
     */
    @TableField(value = "id_number")
    private String idNumber;

    /**
     * 头像
     */
    @TableField(value = "avatar")
    private String avatar;

    /**
     * 状态 0:禁用，1:正常
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 用户邮箱
     */
    @TableField("email")
    private String email;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}