package com.raj.mapper.front;

import com.raj.entity.front.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author RainGrd
* @description 针对表【t_shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-11-24 16:12:35
* @Entity com.raj.entity.front.ShoppingCart
*/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




