package com.raj.entity.backend;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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
 * 菜品管理
 *
 * @TableName t_dish
 */
@Data
@Component
@ApiModel("菜品")
public class Dish implements Serializable {
    /**
     * 主键
     */
    @ApiModelProperty("菜品主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 菜品名称
     */
    @ApiModelProperty("菜品名称")
    @TableField(value = "name")
    private String name;

    /**
     * 菜品分类id
     */
    @ApiModelProperty("菜品分类id")
    @TableField(value = "category_id")
    private Long categoryId;

    /**
     * 菜品价格
     */
    @ApiModelProperty("菜品价格")
    @TableField(value = "price")
    private Double price;

    /**
     * 商品码
     */
    @ApiModelProperty("商品码")
    @TableField(value = "code")
    private String code;

    /**
     * 图片
     */
    @ApiModelProperty("图片")
    @TableField(value = "image")
    private String image;

    /**
     * 描述信息
     */
    @ApiModelProperty("描述信息")
    @TableField(value = "description")
    private String description;

    /**
     * 0 停售 1 起售
     */
    @ApiModelProperty("0 停售 1 起售")
    @TableField(value = "status")
    private Integer status;

    /**
     * 顺序
     */
    @ApiModelProperty("顺序")
    @TableField(value = "sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty("更新时间")
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty("创建人")
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除 0:保留,1删除
     */
    @ApiModelProperty("是否删除 0:保留,1删除")
    @TableLogic(value = "is_deleted")
    private Integer isDeleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}