package com.raj.controller.front;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.dto.OrdersDto;
import com.raj.entity.front.Orders;
import com.raj.service.front.OrdersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api("订单相关接口")
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
    @ApiOperation("分页查询所有历史订单管理接口")
    @GetMapping("/queryOrderHistoryPage.do")
    public Result queryOrderPage(@RequestParam int page, @RequestParam int pageSize) {
        log.info("查询历史订单的分页参数: page:{},pageSize:{}", page, pageSize);
        Page<OrdersDto> ordersPage = ordersService.queryOrderHistoryPage(page, pageSize);
        return Result.success(ordersPage);
    }

    /**
     * 提交订单
     *
     * @return
     */
    @ApiOperation("提交订单接口")
    @PostMapping("/submit.do")
    public Result submit(@RequestBody Orders orders) {
        log.info("提交的订单对象:{}", orders);
        ordersService.saveOrders(orders);
        return Result.success();
    }

    /**
     * 分页查询所有订单
     *
     * @param page
     * @param pageSize
     * @param number    订单号
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return com.raj.Vo.Result
     */
    @GetMapping("/queryOrderPage.do")
    public Result queryOrderPage(int page, int pageSize, String number, String beginTime, String endTime) {
        log.info("查询订单的参数: page:{},pageSize:{},number:{},beginTime:{},endTime:{}", page, pageSize, number, beginTime, endTime);
        Page<OrdersDto> ordersDtoPage = ordersService.queryOrderPage(page, pageSize, number, beginTime, endTime);
        return Result.success(ordersDtoPage);
    }

    /**
     * 订单派送
     *
     * @param orders
     * @return
     */
    @PutMapping("/editOrderDetail.do")
    public Result editOrderDetail(@RequestBody Orders orders) {
        int editOrderDetail = ordersService.editOrderDetail(orders);
        return editOrderDetail > 0 ? Result.success() : Result.error("派送失败");
    }
}
