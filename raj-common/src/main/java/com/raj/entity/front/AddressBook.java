package com.raj.entity.front;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 地址管理
 *
 * @TableName t_address_book
 */
@Data
@Component
@ApiModel("地址")
public class AddressBook implements Serializable {

    private static final long serialVersionUID = -923013194863882281L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;
    /**
     * 用户id
     */
    @NotNull(message = "[用户id]不能为空")
    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Long userId;
    /**
     * 收货人
     */
    @NotBlank(message = "[收货人]不能为空")
    @Size(max = 50, message = "编码长度不能超过50")
    @ApiModelProperty("收货人")
    @Length(max = 50, message = "编码长度不能超过50")
    @TableField("consignee")
    private String consignee;
    /**
     * 性别 0 女 1 男
     */
    @NotNull(message = "[性别 0 女 1 男]不能为空")
    @ApiModelProperty("性别 0 女 1 男")
    @TableField("sex")
    private Integer sex;
    /**
     * 手机号
     */
    @NotBlank(message = "[手机号]不能为空")
    @Size(max = 11, message = "编码长度不能超过11")
    @ApiModelProperty("手机号")
    @Length(max = 11, message = "编码长度不能超过11")
    @TableField("phone")
    private String phone;
    /**
     * 省级区划编号
     */
    @Size(max = 12, message = "编码长度不能超过12")
    @ApiModelProperty("省级区划编号")
    @Length(max = 12, message = "编码长度不能超过12")
    @TableField("province_code")
    private String provinceCode;
    /**
     * 省级名称
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("省级名称")
    @Length(max = 32, message = "编码长度不能超过32")
    @TableField("province_name")
    private String provinceName;
    /**
     * 市级区划编号
     */
    @Size(max = 12, message = "编码长度不能超过12")
    @ApiModelProperty("市级区划编号")
    @Length(max = 12, message = "编码长度不能超过12")
    @TableField("city_code")
    private String cityCode;
    /**
     * 市级名称
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("市级名称")
    @Length(max = 32, message = "编码长度不能超过32")
    @TableField("city_name")
    private String cityName;
    /**
     * 区级区划编号
     */
    @Size(max = 12, message = "编码长度不能超过12")
    @ApiModelProperty("区级区划编号")
    @Length(max = 12, message = "编码长度不能超过12")
    @TableField("district_code")
    private String districtCode;
    /**
     * 区级名称
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("区级名称")
    @Length(max = 32, message = "编码长度不能超过32")
    @TableField("district_code")
    private String districtName;
    /**
     * 详细地址
     */
    @Size(max = 200, message = "编码长度不能超过200")
    @ApiModelProperty("详细地址")
    @Length(max = 200, message = "编码长度不能超过200")
    @TableField("detail")
    private String detail;
    /**
     * 标签
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @ApiModelProperty("标签")
    @Length(max = 100, message = "编码长度不能超过100")
    @TableField("label")
    private String label;
    /**
     * 默认 0 否 1是
     */
    @NotNull(message = "[默认 0 否 1是]不能为空")
    @ApiModelProperty("默认 0 否 1是")
    @TableField("is_default")
    private Integer isDefault;
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
     * 是否删除 0：未删，1：删除
     */
    @NotNull(message = "[是否删除 0：未删，1：删除]不能为空")
    @ApiModelProperty("是否删除 0：未删，1：删除")
    @TableLogic(value = "is_deleted")
    private Integer isDeleted;
}
