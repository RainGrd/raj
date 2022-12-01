package com.raj.entity.backend;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
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
import org.springframework.stereotype.Component;

/**
 * 套餐
 *
 * @TableName t_setmeal
 */
@Data
@Component
@ApiModel("套餐")
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 2130903789443898085L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId(value = "id")
    private Long id;
    /**
     * 菜品分类id
     */
    @NotNull(message = "[菜品分类id]不能为空")
    @ApiModelProperty("菜品分类id")
    @TableField(value = "category_id")
    private Long categoryId;
    /**
     * 套餐名称
     */
    @NotBlank(message = "[套餐名称]不能为空")
    @Size(max = 64, message = "编码长度不能超过64")
    @ApiModelProperty("套餐名称")
    @TableField(value = "name")
    private String name;
    /**
     * 套餐价格
     */
    @NotNull(message = "[套餐价格]不能为空")
    @ApiModelProperty("套餐价格")
    @TableField(value = "price")
    private BigDecimal price;
    /**
     * 状态 0:停用 1:启用
     */
    @ApiModelProperty("状态 0:停用 1:启用")
    @TableField(value = "status")
    private Integer status;
    /**
     * 编码
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @TableField(value = "code")
    @ApiModelProperty("编码")
    private String code;
    /**
     * 描述信息
     */
    @Size(max = 512, message = "编码长度不能超过512")
    @ApiModelProperty("描述信息")
    @TableField(value = "description")
    private String description;
    /**
     * 图片
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("图片")
    @TableField(value = "image")
    private String image;
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
