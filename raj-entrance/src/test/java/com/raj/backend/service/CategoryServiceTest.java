package com.raj.backend.service;

import com.raj.entity.backend.Category;
import com.raj.service.backend.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/16 8:27
 * FileName: CategoryServiceTest
 * Description:
 */
@SpringBootTest
public class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    public void queryCategoryListByTypeTest() {
        Category category = new Category();
        category.setType(1);
        List<Category> categories = categoryService.queryCategoryListByCategory(category);
        System.out.println("categories = " + categories);
    }
}
