package com.raj.entity.backend;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 菜品口味关系表
 * </p>
 *
 * @author RainGrd
 * @since 2022-11-16
 */
@Data
@Component
@ApiModel("菜品口味关系")
public class DishFlavor implements Serializable {
    private static final long serialVersionUID = -1448118919680390140L;
    /**
     * 主键
     */
    @ApiModelProperty("菜品口味关系主键")
    @TableId(value = "id")
    private Long id;

    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    @TableField("dish_id")
    private Long dishId;

    /**
     * 口味名称
     */
    @ApiModelProperty("口味名称")
    @TableField("name")
    private String name;

    /**
     * 口味数据list
     */
    @ApiModelProperty("口味数据list")
    @TableField("value")
    private String value;


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
     * 是否删除 0:保留,1:删除
     */
    @ApiModelProperty("是否删除 0:保留,1:删除")
    @TableLogic(value = "is_deleted", delval = "1")
    private Integer isDeleted;
}
