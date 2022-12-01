package com.raj.service.front.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.constants.CommonEnum;
import com.raj.dto.OrdersDto;
import com.raj.entity.front.*;
import com.raj.service.front.OrdersService;
import com.raj.exception.BaseRuntimeException;
import com.raj.mapper.front.AddressBookMapper;
import com.raj.mapper.front.OrdersMapper;
import com.raj.mapper.front.ShoppingCartMapper;
import com.raj.service.front.OrderDetailService;
import com.raj.holder.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author RainGrd
 * @description 针对表【t_orders(订单表)】的数据库操作Service实现
 * @createDate 2022-11-25 16:42:31
 */
@Service
@Slf4j
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
        implements OrdersService {

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Resource
    private AddressBookMapper addressBookMapper;

    @Resource
    private OrderDetailService orderDetailService;

    @Override
    @Transactional
    public void saveOrders(Orders orders) {
        //获取当前用户
        User user = UserHolder.getUser();
        //查询当前当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> shoppingCartMapperLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartMapperLambdaQueryWrapper.eq(user.getId() != null, ShoppingCart::getUserId, user.getId());
        List<ShoppingCart> shoppingCarts = shoppingCartMapper.selectList(shoppingCartMapperLambdaQueryWrapper);
        //判断购物车是否为空
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw new BaseRuntimeException("购物车为空!不能下单");
        }
        // 根据地址id查询地址数据
        AddressBook addressBook = addressBookMapper.selectById(orders.getAddressBookId());
        if (addressBook == null) {
            throw new BaseRuntimeException("地址为空!不能下单");
        }
        /*封装数据*/
        //设置订单号和id
        long id = IdWorker.getId();
        orders.setId(id);
        orders.setNumber(String.valueOf(id));
        //设置订单状态 待派送
        orders.setStatus(Integer.valueOf(CommonEnum.ORDERS_STATUS_DELIVERED.getValue()));
        //设置金额并封装订单明细集合
        AtomicInteger atomic = new AtomicInteger(0);
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(id);
            // 设置菜品或者套餐的数量
            orderDetail.setNumber(item.getNumber());
            // 菜品口味
            orderDetail.setDishFlavor(item.getDishFlavor());
            // 设置菜品id
            orderDetail.setDishId(item.getDishId());
            // 设置套餐id
            orderDetail.setSetmealId(item.getSetmealId());
            // 订单名称
            orderDetail.setName(item.getName());
            // 订单图片
            orderDetail.setImage(item.getImage());
            // 订单最终成交金额
            orderDetail.setAmount(item.getAmount());
            atomic.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        orders.setAmount(BigDecimal.valueOf(atomic.intValue()));
        orders.setUserId(user.getId());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setUserName(user.getName());
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        //设置地址
        String address = ((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        orders.setAddress(address);
        //插入订单
        ordersMapper.insert(orders);
        //插入订单明细
        orderDetailService.saveBatch(orderDetails);
        //清空当前用户购物车
        shoppingCartMapper.delete(shoppingCartMapperLambdaQueryWrapper);
    }

    @Override
    public Page<OrdersDto> queryOrderPage(int page, int pageSize) {
        Page<Orders> ordersPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Orders> ordersLambdaQueryWrapper = new LambdaQueryWrapper<>();
        Page<Orders> selectPage = ordersMapper.selectPage(ordersPage, ordersLambdaQueryWrapper);
        Page<OrdersDto> ordersDtoPage = new Page<>();
        //复制属性
        BeanUtils.copyProperties(selectPage, ordersDtoPage, "records");
        List<Orders> records = selectPage.getRecords();
        List<OrdersDto> ordersDtoList = records.stream().map((item) -> {
            OrdersDto ordersDto = new OrdersDto();
            //复制属性
            BeanUtils.copyProperties(item, ordersDto);
            //根据订单id查询订单明细
            LambdaQueryWrapper<OrderDetail> orderDetailLambdaQueryWrapper = new LambdaQueryWrapper<>();
            orderDetailLambdaQueryWrapper.eq(OrderDetail::getOrderId, item.getId());
            List<OrderDetail> list = orderDetailService.list(orderDetailLambdaQueryWrapper);
            log.info("订单明细集合:{}", list);
            ordersDto.setOrderDetails(list);
            return ordersDto;
        }).collect(Collectors.toList());
        ordersDtoPage.setRecords(ordersDtoList);
        log.info("查询的订单分页对象:{}", ordersDtoList);
        return ordersDtoPage;
    }
}




