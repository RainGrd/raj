package com.raj.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.raj.entity.front.ShoppingCart;
import com.raj.front.service.ShoppingCartService;
import com.raj.front.mapper.ShoppingCartMapper;
import com.raj.holder.UserHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author RainGrd
 * @description 针对表【t_shopping_cart(购物车)】的数据库操作Service实现
 * @createDate 2022-11-24 16:12:35
 */
@Service
@Slf4j
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
        implements ShoppingCartService {
    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    public List<ShoppingCart> queryShoppingCartList(ShoppingCart shoppingCart) {
        Long userId = UserHolder.getUser().getId();
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件 当前用户id
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        return shoppingCartMapper.selectList(lambdaQueryWrapper);
    }

    @Override
    public ShoppingCart saveShoppingCart(ShoppingCart shoppingCart) {
        //获取当前用户
        Long userId = UserHolder.getUser().getId();
        // 获取菜品Id
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> shoppingCartLambdaQueryWrapper = new LambdaQueryWrapper<>();
        shoppingCartLambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        //查询当前菜品或者套餐是否存在于购物车中
        //添加购物车的是菜品
        shoppingCartLambdaQueryWrapper.eq(dishId != null, ShoppingCart::getDishId, dishId);
        //添加到购物车的是套餐
        shoppingCartLambdaQueryWrapper.eq(shoppingCart.getSetmealId() != null, ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        //判断菜品或者的套餐是否相等
        shoppingCartLambdaQueryWrapper.eq(shoppingCart.getDishFlavor() != null, ShoppingCart::getDishFlavor, shoppingCart.getDishFlavor());
        //进行查询
        ShoppingCart cart = shoppingCartMapper.selectOne(shoppingCartLambdaQueryWrapper);
        log.info("查询出来的购物车对象:{}", cart);
        if (cart != null) {
            //如果存在，则在原来的基础上加一
            cart.setNumber(cart.getNumber() + 1);
            //进行更新
            shoppingCartMapper.updateById(cart);
        }
        //如果不存在,则添加数据
        //封装数据
        shoppingCart.setUserId(userId);
        shoppingCart.setCreateTime(LocalDateTime.now());
        //插入数据
        shoppingCartMapper.insert(shoppingCart);
        //返回数据
        cart = shoppingCart;
        log.info("返回的数据:{}", cart);
        return cart;
    }

    @Override
    public ShoppingCart modifyShoppingCart(ShoppingCart shoppingCart) {
        Long dishId = shoppingCart.getDishId();
        LambdaUpdateWrapper<ShoppingCart> shoppingCartLambdaUpdateWrapper = new LambdaUpdateWrapper<>();
        shoppingCartMapper.updateById(shoppingCart);
        return shoppingCart;
    }

    @Override
    public int deleteShoppingCart(ShoppingCart shoppingCart) {
        return shoppingCartMapper.deleteById(shoppingCart);
    }
}




