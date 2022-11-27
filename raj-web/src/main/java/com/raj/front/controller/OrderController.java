package com.raj.front.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.dto.OrdersDto;
import com.raj.entity.front.Orders;
import com.raj.entity.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Classname OrderController
 * @Description 订单控制层
 * @Version 1.0.0
 * @Date 2022/11/25 16:42
 * @Author RainGrd
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Resource
    private OrdersService ordersService;

    /**
     * 分页查询所有订单管理
     *
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/queryOrderPage.do")
    public Object queryOrderPage(@RequestParam int page, @RequestParam int pageSize) {
        Page<OrdersDto> ordersPage = ordersService.queryOrderPage(page, pageSize);
        return Result.success(ordersPage);
    }

    /**
     * 提交订单
     *
     * @return
     */
    @PostMapping("/submit.do")
    public Object submit(@RequestBody Orders orders) {
        log.info("提交的订单对象:{}", orders);
        ordersService.saveOrders(orders);
        return Result.success();
    }
}
