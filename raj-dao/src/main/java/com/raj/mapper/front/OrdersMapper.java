package com.raj.mapper.front;

import com.raj.entity.front.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author RainGrd
* @description 针对表【t_orders(订单表)】的数据库操作Mapper
* @createDate 2022-11-25 16:42:30
* @Entity com.raj.entity.front.Orders
*/
@Mapper
public interface OrdersMapper extends BaseMapper<Orders> {

}




