package com.raj.backend.category.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.entity.backend.Category;
import com.raj.backend.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/14 12:57
 * FileName: CategoryController
 * Description: 分类控制层
 */
@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 分类分页查询
     *
     * @param pageSize
     * @param page
     * @return
     */
    @GetMapping("/queryCategoryForPage.do")
    public Object queryCategoryForPage(int pageSize, int page) {
        //输出日志
        log.info("pageSize:{},page:{}", pageSize, page);
        Page<Category> categoryPage = categoryService.queryCategoryForPage(pageSize, page);
        log.info("输出查询的分页数据:{}", categoryPage.getRecords().toString());
        return Result.success(categoryPage);
    }

    /**
     * 新增分类
     *
     * @param category
     * @return
     */
    @PostMapping("/saveCategory.do")
    public Object saveCategory(@RequestBody Category category) {
        log.info("需要新增的分类对象:{}", category);
        int i = categoryService.saveCategory(category);
        if (i <= 0) {
            return Result.error("添加失败!");
        }
        return Result.success();
    }

    /**
     * 删除分类
     *
     * @param id
     * @return
     */
    @DeleteMapping("/deleteCategoryById.do")
    public Object deleteCategoryById(@RequestParam("ids") Long id) {
        log.info("删除的分类Id:{}", id);
        return categoryService.deleteCategoryById(id);
    }

    /**
     * 修改分类
     *
     * @param category
     * @return
     */
    @PutMapping("/modifyCategoryById.do")
    public Object modifyCategoryById(@RequestBody Category category) {
        int i = categoryService.modifyCategoryById(category);
        if (i < 0) {
            return Result.error("修改失败！");
        }
        return Result.success();
    }

    /**
     * 根据type查询菜品分类
     *
     * @param category 分类实体对象
     * @return
     */
    @GetMapping("/queryCategoryListByCategory.do")
    public Object queryCategoryListByCategory(Category category) {
        log.info("菜品分类对象:{}", category);
        return Result.success(categoryService.queryCategoryListByCategory(category));
    }
}
