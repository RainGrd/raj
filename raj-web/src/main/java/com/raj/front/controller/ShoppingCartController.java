package com.raj.front.controller;

import com.raj.Vo.Result;
import com.raj.entity.front.ShoppingCart;
import com.raj.front.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname ShoppingCartController
 * @Description 购物车控制层
 * @Version 1.0.0
 * @Date 2022/11/22 18:29
 * @Author RainGrd
 */
@RestController
@Slf4j
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

    /**
     * 保存商品到购物车中
     *
     * @param shoppingCart
     * @return
     */
    @PostMapping("/saveShoppingCart.do")
    public Object saveShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        log.info("要保存的商品:{}", shoppingCart);
        //保存商品
        ShoppingCart cart = shoppingCartService.saveShoppingCart(shoppingCart);

        return cart == null ? Result.error("保存失败!!!") : Result.success(cart);
    }

    /**
     * 修改购物车中的商品
     *
     * @param shoppingCart
     * @return
     */
    @PutMapping("/modifyShoppingCart.do")
    public Object modifyShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        log.info("要修改的购物车商品:{}", shoppingCart);
        //调用方法进行修改
        ShoppingCart cart = shoppingCartService.modifyShoppingCart(shoppingCart);
        return cart == null ? Result.error("已经没有菜品和套餐了") : Result.success(cart);
    }

    /**
     * 清空购物车
     *
     * @return
     */
    @DeleteMapping("/deleteShoppingCart.do")
    public Object deleteShoppingCart() {
        //调用删除方法
        int i = shoppingCartService.deleteShoppingCart();
        return i <= 0 ? Result.error("删除失败!!!") : Result.success("成功删除!!!");
    }

}
