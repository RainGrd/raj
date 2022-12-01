package com.raj.service.backend.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.Vo.Result;
import com.raj.entity.backend.Category;
import com.raj.entity.backend.Dish;
import com.raj.entity.backend.Setmeal;
import com.raj.mapper.backend.DishMapper;
import com.raj.mapper.backend.SetmealMapper;
import com.raj.service.backend.CategoryService;
import com.raj.mapper.backend.CategoryMapper;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lenovo
 * @description 针对表【t_category(菜品及套餐分类)】的数据库操作Service实现
 * @createDate 2022-11-14 12:53:11
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    @Resource
    private DishMapper dishMapper;

    @Resource
    private SetmealMapper setmealMapper;

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Page<Category> queryCategoryForPage(int pageSize, int page) {
        Page<Category> categoryPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //倒序条件
        lambdaQueryWrapper.orderByDesc(Category::getUpdateTime);
        return categoryMapper.selectPage(categoryPage, lambdaQueryWrapper);
    }

    @Override
    public int saveCategory(Category category) {
        return categoryMapper.insert(category);
    }

    @Override
    public Result deleteCategoryById(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据categoryId查询菜品
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        Long count1 = dishMapper.selectCount(dishLambdaQueryWrapper);
        //存在
        if (count1 > 1) {
            //抛出业务异常
            throw new BaseRuntimeException("此分类已经被菜品关联");
        }
        //根据categoryId查询套餐
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        Long count2 = setmealMapper.selectCount(setmealLambdaQueryWrapper);
        //存在
        if (count2 > 0) {
            //抛出业务异常
            throw new BaseRuntimeException("此分类已经被套餐关联");
        }
        int i = categoryMapper.deleteById(id);
        if (i <= 0) {
            return Result.error("删除失败!");
        }
        return Result.success();
    }

    @Override
    public int modifyCategoryById(Category category) {
        return categoryMapper.updateById(category);
    }


    @Override
    public List<Category> queryCategoryListByCategory(Category category) {
        LambdaQueryWrapper<Category> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        lambdaQueryWrapper.eq(category.getType() != null, Category::getType, category.getType());
        //添加排序条件
        lambdaQueryWrapper.orderByAsc(Category::getSort).orderByDesc(Category::getUpdateTime);
        return categoryMapper.selectList(lambdaQueryWrapper);
    }
}




