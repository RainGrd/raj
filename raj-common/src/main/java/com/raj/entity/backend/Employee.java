package com.raj.entity.backend;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 员工信息
 *
 * @TableName t_employee
 */
@Data
@Component
@ApiModel("员工")
public class Employee implements Serializable {

    private static final long serialVersionUID = 7432809563271632779L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;
    /**
     * 姓名
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("name")
    @TableField(value = "name")
    private String name;
    /**
     * 用户名
     */
    @NotBlank(message = "[用户名]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("username")
    @TableField(value = "username")
    private String username;
    /**
     * 密码
     */
    @Size(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty("密码")
    @TableField(value = "password")
    private String password;
    /**
     * 手机号
     */
    @NotBlank(message = "[手机号]不能为空")
    @Size(max = 11, message = "编码长度不能超过11")
    @ApiModelProperty("手机号")
    @TableField(value = "phone")
    private String phone;
    /**
     * 性别
     */
    @NotBlank(message = "[性别]不能为空")
    @Size(max = 2, message = "编码长度不能超过2")
    @ApiModelProperty("性别")
    @TableField(value = "sex")
    private String sex;
    /**
     * 身份证号
     */
    @NotBlank(message = "[身份证号]不能为空")
    @Size(max = 18, message = "编码长度不能超过18")
    @ApiModelProperty("身份证号")
    @TableField(value = "id_number")
    private String idNumber;
    /**
     * 状态 0:禁用，1:正常
     */
    @NotNull(message = "[状态 0:禁用，1:正常]不能为空")
    @ApiModelProperty("状态 0:禁用，1:正常")
    @TableField(value = "status")
    private Integer status;
    /**
     * 创建时间
     */
    @NotNull(message = "[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @NotNull(message = "[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    @NotNull(message = "[创建人]不能为空")
    @ApiModelProperty("创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;
    /**
     * 修改人
     */
    @NotNull(message = "[修改人]不能为空")
    @ApiModelProperty("修改人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;
    /**
     * 删除状态,0:未删除.1删除
     */
    @NotNull(message = "[删除状态,0:未删除.1删除]不能为空")
    @ApiModelProperty("删除状态,0:未删除.1删除")
    @TableField(value = "dev_status")
    private String devStatus;
    /**
     * 员工分类,0是管理员，1是普通员工
     */
    @NotNull(message = "[员工分类,0是管理员，1是普通员工]不能为空")
    @ApiModelProperty("员工分类,0是管理员，1是普通员工")
    @TableField(value = "employee_classify")
    private Integer employeeClassify;

}
