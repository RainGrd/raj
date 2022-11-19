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
@Controller
@Slf4j
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
    @GetMapping("/backend/category/queryCategoryForPage.do")
    @ResponseBody
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
    @PostMapping("/backend/category/saveCategory.do")
    @ResponseBody
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
    @DeleteMapping("/backend/category/deleteCategoryById.do")
    @ResponseBody
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
    @PutMapping("/backend/category/modifyCategoryById.do")
    @ResponseBody
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
     * @param type
     * @return
     */
    @RequestMapping("/backend/category/queryCategoryListByType.do")
    @ResponseBody
    public Object queryCategoryListByType(Integer type) {
        return Result.success(categoryService.queryCategoryListByType(type));
    }
}
