package com.raj.front.mapper;

import com.raj.entity.front.ShoppingCart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author RainGrd
* @description 针对表【t_shopping_cart(购物车)】的数据库操作Mapper
* @createDate 2022-11-22 18:20:51
* @Entity com.raj.entity.front.ShoppingCart
*/
@Repository
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {

}




