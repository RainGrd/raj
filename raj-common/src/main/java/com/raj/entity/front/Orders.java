package com.raj.entity.front;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.time.LocalDateTime;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.stereotype.Component;

/**
 * 订单表
 *
 * @TableName t_orders
 */
@Data
@Component
@ApiModel("订单")
public class Orders implements Serializable {

    private static final long serialVersionUID = -1444735615585674555L;
    /**
     * 主键
     */
    @NotNull(message = "[主键]不能为空")
    @ApiModelProperty("主键")
    @TableId("id")
    private Long id;
    /**
     * 订单号
     */
    @Size(max = 50, message = "编码长度不能超过50")
    @ApiModelProperty("订单号")
    @Length(max = 50, message = "编码长度不能超过50")
    @TableField("number")
    private String number;
    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    @NotNull(message = "[订单状态 1待付款，2待派送，3已派送，4已完成，5已取消]不能为空")
    @ApiModelProperty("订单状态 1待付款，2待派送，3已派送，4已完成，5已取消")
    @TableField("status")
    private Integer status;
    /**
     * 下单用户
     */
    @NotNull(message = "[下单用户]不能为空")
    @ApiModelProperty("下单用户")
    @TableField("user_id")
    private Long userId;
    /**
     * 地址id
     */
    @NotNull(message = "[地址id]不能为空")
    @ApiModelProperty("地址id")
    @TableField("address_book_id")
    private Long addressBookId;
    /**
     * 下单时间
     */
    @NotNull(message = "[下单时间]不能为空")
    @ApiModelProperty("下单时间")
    @TableField("order_time")
    private LocalDateTime orderTime;
    /**
     * 结账时间
     */
    @NotNull(message = "[结账时间]不能为空")
    @ApiModelProperty("结账时间")
    @TableField("checkout_time")
    private LocalDateTime checkoutTime;
    /**
     * 支付方式 1微信,2支付宝
     */
    @NotNull(message = "[支付方式 1微信,2支付宝]不能为空")
    @ApiModelProperty("支付方式 1微信,2支付宝")
    @TableField("pay_method")
    private Integer payMethod;
    /**
     * 实收金额
     */
    @NotNull(message = "[实收金额]不能为空")
    @ApiModelProperty("实收金额")
    @TableField("amount")
    private BigDecimal amount;
    /**
     * 备注
     */
    @Size(max = 100, message = "编码长度不能超过100")
    @ApiModelProperty("备注")
    @Length(max = 100, message = "编码长度不能超过100")
    @TableField("remark")
    private String remark;
    /**
     *
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField("phone")
    private String phone;
    /**
     *
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField("address")
    private String address;
    /**
     * 用户名称
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("用户名称")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField("user_name")
    private String userName;
    /**
     * 收货人
     */
    @Size(max = 255, message = "编码长度不能超过255")
    @ApiModelProperty("收货人")
    @Length(max = 255, message = "编码长度不能超过255")
    @TableField("consignee")
    private String consignee;
}
