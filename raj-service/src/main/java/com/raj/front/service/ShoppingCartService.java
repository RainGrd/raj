package com.raj.front.service;

import com.raj.entity.front.ShoppingCart;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author RainGrd
* @description 针对表【t_shopping_cart(购物车)】的数据库操作Service
* @createDate 2022-11-22 18:20:51
*/
public interface ShoppingCartService extends IService<ShoppingCart> {
    /**
     * 查询购物车列表
     * @param shoppingCart
     * @return
     */
    List<ShoppingCart> queryShoppingCartList(ShoppingCart shoppingCart);
}
