package com.raj.backend.food.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.entity.backend.Dish;
import com.raj.backend.service.DishService;
import com.raj.dto.DishDto;
import com.raj.exception.BaseException;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/14 18:53
 * FileName: DishController
 * Description: 菜品控制层
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {


    @Autowired
    private DishService dishService;


    /**
     * 分类分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    @GetMapping("/queryDishForPage.do")
    public Object queryDishForPage(int pageSize, int page, String name) {
        //输出日志
        log.info("pageSize:{},page:{},name:{}", pageSize, page, name);
        Page<DishDto> dishPage = dishService.queryDishForPage(pageSize, page, name);
        log.info("输出查询的分页数据:{}", dishPage.getRecords().toString());
        return Result.success(dishPage);
    }

    /**
     * 新增分类
     *
     * @param dishDto 菜品数据传输对象
     * @return
     */
    @PostMapping("/saveDish.do")
    public Object saveDish(@RequestBody DishDto dishDto) {
        log.info("需要新增的菜品数据传输对象:{}", dishDto);
        dishService.saveDishByDishDto(dishDto);
        return Result.success();
    }

    /**
     * 根据id查询菜品对象
     *
     * @param id
     * @return
     */
    @GetMapping("/getDishById.do/{id}")
    public Object getDishById(@PathVariable Long id) {
        DishDto dishDto = dishService.queryDishById(id);
        if (dishDto == null) {
            return Result.error("没有查询到数据");
        }
        return Result.success(dishDto);
    }


    /**
     * 删除菜品
     * 1.根据id数组逻辑删除菜品
     * 2.获取菜品口味的Id
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteDishById.do")
    public Object deleteDishById(@RequestParam List<Long> ids) throws BaseException {
        log.info("要删除的id数组:{}", ids);
        int i = dishService.deleteDishByIds(ids);
        if (i <= 0) {
            //抛出业务异常
            throw new BaseRuntimeException("系统繁忙,正在维护中！");
        }
        return Result.success();
    }

    /**
     * 根据Id修改菜品
     *
     * @param dishDto
     * @return
     */
    @PutMapping("/modifyDishById.do")
    public Object modifyDishById(@RequestBody DishDto dishDto) throws BaseException {
        log.info("需要修改的菜品dto:{}", dishDto);
        dishService.modifyDishById(dishDto);
        return Result.success();
    }

    /**
     * 批量批量启售和批量停售方法
     *
     * @param status
     * @param ids
     * @return
     * @throws BaseException
     */
    @PostMapping("/modifyDishByStatus.do/{status}")
    public Object modifyDishByStatus(@PathVariable Integer status, @RequestParam("ids") Long[] ids) throws BaseException {
        log.info("要修改的菜品状态:{}", status);
        log.info("要修改菜品状态的的id数组:{}", Arrays.asList(ids));
        int i = dishService.modifyDishByStatus(status, ids);
        if (i <= 0) {
            //抛出业务异常
            throw new BaseRuntimeException("系统繁忙,正在维护中！");
        }
        return Result.success();
    }

    /**
     * 根据分类Id查询菜品列表
     *
     * @param dish
     * @return
     */
    @GetMapping("/queryDishListByCategoryId.do")
    public Object queryDishListByCategoryId(Dish dish) {
        log.info("菜品对象:{}", dish);
        return Result.success(dishService.queryDishListByCategoryId(dish));
    }
}
