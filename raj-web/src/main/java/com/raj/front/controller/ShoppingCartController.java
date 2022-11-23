package com.raj.front.controller;

import com.raj.Vo.Result;
import com.raj.entity.front.ShoppingCart;
import com.raj.front.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname ShoppingCartController
 * @Description 购物车控制层
 * @Version 1.0.0
 * @Date 2022/11/22 18:29
 * @Author RainGrd
 */
@RestController
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;


    /**
     * 查询购物车列表
     *
     * @param shoppingCart 购物车对象
     * @return
     */
    @GetMapping("/queryShoppingCartList.do")
    public Object queryShoppingCartList(ShoppingCart shoppingCart) {
        List<ShoppingCart> shoppingCarts = shoppingCartService.queryShoppingCartList(shoppingCart);
        return Result.success(shoppingCarts);
    }

}
