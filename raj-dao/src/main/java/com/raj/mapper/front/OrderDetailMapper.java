package com.raj.mapper.front;

import com.raj.entity.front.OrderDetail;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author RainGrd
* @description 针对表【t_order_detail(订单明细表)】的数据库操作Mapper
* @createDate 2022-11-23 17:02:27
* @Entity com.raj.entity.front.OrderDetail
*/
@Repository
public interface OrderDetailMapper extends BaseMapper<OrderDetail> {

}




