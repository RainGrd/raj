package com.raj.backend.service;

import com.raj.entity.backend.Category;
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
        List<Category> categories = categoryService.queryCategoryListByType(1);
        System.out.println("categories = " + categories);
    }
}
