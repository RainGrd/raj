package com.raj.backend.combo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.backend.service.DishService;
import com.raj.backend.service.SetmealService;
import com.raj.dto.SetmealDto;
import com.raj.entity.backend.Setmeal;
import com.raj.exception.BaseException;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/17 14:05
 * FileName: SetmealController
 * Description: 套餐前端控制器
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {


    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;


    /**
     * 分类分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    @GetMapping("/querySetmealForPage.do")
    public Object querySetmealForPage(int pageSize, int page, String name) {
        //输出日志
        log.info("pageSize:{},page:{},name:{}", pageSize, page, name);
        Page<SetmealDto> setmealPage = setmealService.querySetmealForPage(pageSize, page, name);
        log.info("输出查询的分页数据:{}", setmealPage.getRecords().toString());
        return Result.success(setmealPage);
    }

    /**
     * 新增分类
     *
     * @param setmealDto 套餐菜品数据传输对象
     * @return
     */
    @PostMapping("/saveSetmeal.do")
    public Object saveSetmeal(@RequestBody SetmealDto setmealDto) {
        log.info("需要新增的套餐菜品数据传输对象:{}", setmealDto);
        setmealService.saveSetmealBySetmealDto(setmealDto);
        return Result.success();
    }

    /**
     * 根据id查询菜品对象
     *
     * @param id
     * @return
     */
    @GetMapping("/getSetmealById.do/{id}")
    public Object getSetmealById(@PathVariable Long id) {
        log.info("要查询的id:{}", id);
        SetmealDto setmealDto = setmealService.querySetmealById(id);
        if (setmealDto == null) {
            return Result.error("没有查询到数据");
        }
        return Result.success(setmealDto);
    }


    /**
     * 删除菜品
     * 1.根据id数组逻辑删除菜品
     * 2.获取菜品口味的Id
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/deleteSetmealById.do")
    public Object deleteSetmealById(@RequestParam List<Long> ids) throws BaseException {
        log.info("要删除的id数组:{}", ids);
        int i = setmealService.deleteSetmealByIds(ids);
        if (i <= 0) {
            //抛出业务异常
            throw new BaseRuntimeException("系统繁忙,正在维护中！");
        }
        return Result.success();
    }

    /**
     * 根据Id修改菜品
     *
     * @param setmealDto
     * @return
     */
    @PutMapping("/modifySetmealById.do")
    public Object modifySetmealById(@RequestBody SetmealDto setmealDto) throws BaseException {
        log.info("传输过来的套餐dto:{}", setmealDto);
        setmealService.modifySetmealById(setmealDto);
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
    @PostMapping("/modifySetmealByStatus.do/{status}")
    public Object modifySetmealByStatus(@PathVariable Integer status, @RequestParam("ids") Long[] ids) throws BaseException {
        log.info("要修改的菜品状态:{}", status);
        log.info("要修改菜品状态的的id数组:{}", Arrays.asList(ids));
        int i = setmealService.modifySetmealByStatus(status, ids);
        if (i <= 0) {
            //抛出业务异常
            throw new BaseRuntimeException("系统繁忙,正在维护中！");
        }
        return Result.success();
    }

    /**
     * 查询套餐集合
     *
     * @param setmeal
     * @return
     */
    @GetMapping("/querySetmealList.do")
    public Object querySetmealList(Setmeal setmeal) {
        log.info("要查询的套餐对象:{}", setmeal);
        return Result.success(setmealService.querySetmealList(setmeal));
    }

}
