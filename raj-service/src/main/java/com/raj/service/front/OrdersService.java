package com.raj.service.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.dto.OrdersDto;
import com.raj.entity.front.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author RainGrd
* @description 针对表【t_orders(订单表)】的数据库操作Service
* @createDate 2022-11-25 16:42:31
*/
public interface OrdersService extends IService<Orders> {

    void saveOrders(Orders orders);

    Page<OrdersDto> queryOrderPage(int page, int pageSize);
}
