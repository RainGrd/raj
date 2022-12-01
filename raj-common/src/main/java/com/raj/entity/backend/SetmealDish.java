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
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 套餐菜品关系
 *
 * @TableName t_setmeal_dish
 */
@Data
@Component
@ApiModel("套餐菜品")
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1096030251464705516L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;
    /**
     * 套餐id
     */
    @NotBlank(message = "[套餐id ]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @TableField("setmeal_id")
    @ApiModelProperty("套餐id ")
    @Length(max = 32, message = "编码长度不能超过32")
    private Long setmealId;
    /**
     * 菜品id
     */
    @NotBlank(message = "[菜品id]不能为空")
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("菜品id")
    @Length(max = 32, message = "编码长度不能超过32")
    @TableField("dish_id")
    private Long dishId;
    /**
     * 菜品名称 （冗余字段）
     */
    @Size(max = 32, message = "编码长度不能超过32")
    @ApiModelProperty("菜品名称 （冗余字段）")
    @Length(max = 32, message = "编码长度不能超过32")
    @TableField("name")
    private String name;
    /**
     * 菜品原价（冗余字段）
     */
    @ApiModelProperty("菜品原价（冗余字段）")
    @TableField("price")
    private BigDecimal price;
    /**
     * 份数
     */
    @NotNull(message = "[份数]不能为空")
    @ApiModelProperty("份数")
    @TableField("copies")
    private Integer copies;
    /**
     * 排序
     */
    @NotNull(message = "[排序]不能为空")
    @ApiModelProperty("排序")
    @TableField("sort")
    private Integer sort;
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
