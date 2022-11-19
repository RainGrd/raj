package com.raj.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.entity.backend.Dish;
import com.raj.dto.DishDto;
import com.raj.exception.BaseException;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【t_dish(菜品管理)】的数据库操作Service
 * @createDate 2022-11-14 16:40:46
 */
public interface DishService  //extends IService<Dish>
 {

    /**
     * 分页查询
     *
     * @param pageSize
     * @param page
     * @param name
     * @return
     */
    Page<DishDto> queryDishForPage(int pageSize, int page, String name);

    /**
     * 根据dishDto保存菜品
     * @param dishDto
     * @return
     */
    void saveDishByDishDto(DishDto dishDto);

     DishDto queryDishById(Long id);

     void modifyDishById(DishDto dishDto) throws BaseException;

     int deleteDishByIds(List<Long> ids);

     int modifyDishByStatus(Integer status,Long[] ids);

     List<Dish> queryDishListByCategoryId(Dish dish);

 }
