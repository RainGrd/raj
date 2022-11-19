package com.raj.entity.backend;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 套餐菜品关系
 * </p>
 *
 * @author RainGrd
 * @since 2022-11-17
 */
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 套餐id 
     */
    @TableField("setmeal_id")
    private Long setmealId;

    /**
     * 菜品id
     */
    @TableField("dish_id")
    private Long dishId;

    /**
     * 菜品名称 （冗余字段）
     */
    @TableField("name")
    private String name;

    /**
     * 菜品原价（冗余字段）
     */
    @TableField("price")
    private Double price;

    /**
     * 份数
     */
    @TableField("copies")
    private Integer copies;

    /**
     * 排序
     */
    @TableField("sort")
    private Integer sort;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/HUNAN")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private Long createUser;

    /**
     * 修改人
     */
    @TableField(value = "update_user", fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    /**
     * 是否删除
     */
    @TableLogic("is_deleted")
    private Integer isDeleted;

    @Override
    public String toString() {
        return "SetmealDish{" +
            "id=" + id +
            ", setmealId=" + setmealId +
            ", dishId=" + dishId +
            ", name=" + name +
            ", price=" + price +
            ", copies=" + copies +
            ", sort=" + sort +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", createUser=" + createUser +
            ", updateUser=" + updateUser +
            ", isDeleted=" + isDeleted +
        "}";
    }
}
