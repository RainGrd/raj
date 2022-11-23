package com.raj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.entity.front.ShoppingCart;
import com.raj.front.service.ShoppingCartService;
import com.raj.front.mapper.ShoppingCartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RainGrd
 * @description 针对表【t_shopping_cart(购物车)】的数据库操作Service实现
 * @createDate 2022-11-22 18:20:51
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> queryShoppingCartList(ShoppingCart shoppingCart) {
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件
//        lambdaQueryWrapper.eq()
        return shoppingCartMapper.selectList(lambdaQueryWrapper);
    }
}




