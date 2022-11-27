package com.raj.dto;

import com.raj.entity.front.OrderDetail;
import com.raj.entity.front.Orders;
import lombok.Data;

import java.util.List;

@Data
public class OrdersDto extends Orders {

    private static final long serialVersionUID = 2184600903676743723L;
    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
