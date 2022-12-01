package com.raj.mapper.backend;

import com.raj.entity.backend.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lenovo
 * @description 针对表【t_dish(菜品管理)】的数据库操作Mapper
 * @createDate 2022-11-14 16:40:46
 * @Entity com.raj.entity.backend.Dish
 */
@Repository
public interface DishMapper extends BaseMapper<Dish> {



}




