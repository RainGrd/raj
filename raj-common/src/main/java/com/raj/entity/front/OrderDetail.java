package com.raj.entity.front;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 订单明细表
 *
 * @TableName t_order_detail
 */
@Data
@Component
@ApiModel("订单明细")
public class OrderDetail implements Serializable {

    private static final long serialVersionUID = -4354805395416696147L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;
    /**
     * 名字
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @ApiModelProperty("名字")
    @Length(max = 50, message = "编码长度不能超过50")
    @TableField("name")
    private String name;
    /**
     * 图片
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @ApiModelProperty("图片")
    @Length(max = 100, message = "编码长度不能超过100")
    @TableField("image")
    private String image;
    /**
     * 订单id
     */
    @NotNull(message = "[订单id]不能为空")
    @ApiModelProperty("订单id")
    @TableField("order_id")
    private Long orderId;
    /**
     * 菜品id
     */
    @ApiModelProperty("菜品id")
    @TableField("dish_id")
    private Long dishId;
    /**
     * 套餐id
     */
    @ApiModelProperty("套餐id")
    @TableField("setmeal_id")
    private Long setmealId;
    /**
     * 口味
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @ApiModelProperty("口味")
    @Length(max = 50, message = "编码长度不能超过50")
    @TableField("dish_flavor")
    private String dishFlavor;
    /**
     * 数量
     */
    @NotNull(message = "[数量]不能为空")
    @ApiModelProperty("数量")
    @TableField("number")
    private Integer number;
    /**
     * 金额
     */
    @NotNull(message = "[金额]不能为空")
    @ApiModelProperty("金额")
    @TableField("amount")
    private BigDecimal amount;
}
