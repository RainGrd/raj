package com.raj.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.entity.backend.Category;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【t_category(菜品及套餐分类)】的数据库操作Service
 * @createDate 2022-11-14 12:53:11
 */
public interface CategoryService {

    Page<Category> queryCategoryForPage(int pageSize, int page);

    int saveCategory(Category category);

    Result deleteCategoryById(Long id);

    int modifyCategoryById(Category category);

    /**
     * 根据type来查询菜品分类集合
     *
     * @param type
     * @return
     */
    List<Category> queryCategoryListByType(Integer type);
}
