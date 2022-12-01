package com.raj.service.front.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.entity.front.OrderDetail;
import com.raj.service.front.OrderDetailService;
import com.raj.mapper.front.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author RainGrd
* @description 针对表【t_order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2022-11-23 17:02:27
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




