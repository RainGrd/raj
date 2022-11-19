package com.raj.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.entity.backend.Category;
import com.raj.entity.backend.Dish;
import com.raj.entity.backend.DishFlavor;
import com.raj.backend.mapper.CategoryMapper;
import com.raj.backend.service.DishService;
import com.raj.backend.mapper.DishMapper;
import com.raj.backend.service.IDishFlavorService;
import com.raj.constants.CommonEnum;
import com.raj.dto.DishDto;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【t_dish(菜品管理)】的数据库操作Service实现
 * @createDate 2022-11-14 16:40:46
 */
@Service
@Slf4j
public class DishServiceImpl
        //extends ServiceImpl<DishMapper, Dish>
        implements DishService {

    @Resource
    private DishMapper dishMapper;

    @Autowired
    private IDishFlavorService dishFlavorService;

    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public Page<DishDto> queryDishForPage(int pageSize, int page, String name) {
        Page<DishDto> dishDtoPage = new Page<>();
        Page<Dish> dishPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Dish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Dish::getName, name);
        lambdaQueryWrapper.eq(Dish::getIsDeleted, CommonEnum.IS_DELETED_NO.getValue());
        //根据修改时间来排序
        lambdaQueryWrapper.orderByDesc(Dish::getUpdateTime);
        //执行查询
        dishMapper.selectPage(dishPage, lambdaQueryWrapper);
        // 拷贝属性，除了records属性以外
        BeanUtils.copyProperties(dishPage, dishDtoPage, "records");
        //创建DishDto集合对象
        List<DishDto> dishDtoList = new ArrayList<>();
        //遍历records循环
        for (Dish record : dishPage.getRecords()) {
            DishDto dishDto = new DishDto();
            log.info("要拷贝的菜品对象:{}", record);
            //再次拷贝属性
            BeanUtils.copyProperties(record, dishDto);
            //根据id查询分类对象
            Category category = categoryMapper.selectById(record.getCategoryId());
            if (category != null) {
                //封装属性
                dishDto.setCategoryName(category.getName());
            }
            log.info("再次拷贝属性的dto:{}", dishDto);
            dishDtoList.add(dishDto);
        }
        //添加遍历完成之后的数据
        dishDtoPage.setRecords(dishDtoList);
        //进行遍历
        /*List<DishDto> collect = records.stream().map((item) -> {
            DishDto dishDto = new DishDto();
            //再次拷贝属性
            BeanUtils.copyProperties(item, dishDto);
            //根据id查询分类对象
            Category category = categoryMapper.selectById(item.getCategoryId());
            dishDto.setCategoryName(category.getName());
            return dishDto;
        }).collect(Collectors.toList());
        dishDtoPage.setRecords(collect);*/
        return dishDtoPage;
    }

    @Override
    @Transactional
    public void saveDishByDishDto(DishDto dishDto) {
        try {
            // 将菜品对象插入到t_dish表中
            dishMapper.insert(dishDto);
            List<DishFlavor> flavors = dishDto.getFlavors();
            //获取菜品id
            Long dishId = dishDto.getId();
            log.info("菜品Id:{}", dishId);
            //将菜品的id添加进集合中
            List<DishFlavor> collect = flavors.stream().peek((item) -> item.setDishId(dishId)).collect(Collectors.toList());
            //批量插入到t_dish_flavor表中
            boolean saveBatch = dishFlavorService.saveBatch(collect);
        } catch (Exception e) {
            throw new BaseRuntimeException("系统正在维护中！");
        }
    }

    @Override
    @Transactional
    public DishDto queryDishById(Long id) {
        DishDto dishDto = new DishDto();
        //拷贝数据
        Dish dish = dishMapper.selectById(id);
        //根据菜品的分类id查询菜品分类
        Category category = categoryMapper.selectById(dish.getCategoryId());
        log.info("即将被拷贝的菜品属性:{}", dish);
        BeanUtils.copyProperties(dish, dishDto);
        log.info("拷贝完成之后的菜品属性:{}", dishDto);
        //查询当前菜品的对应的口味信息，从dish_flavor表查询
        LambdaQueryWrapper<DishFlavor> dishFlavorLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件，口味id
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getDishId, dish.getId());
        dishFlavorLambdaQueryWrapper.eq(DishFlavor::getIsDeleted, CommonEnum.IS_DELETED_NO.getValue());
        List<DishFlavor> list = dishFlavorService.list(dishFlavorLambdaQueryWrapper);
        //封装数据
        dishDto.setFlavors(list);
        dishDto.setCategoryName(category.getName());
        //返回数据
        return dishDto;
    }

    @Override
    @Transactional
    public void modifyDishById(DishDto dishDto) {
        Dish dish = new Dish();
        try {
            // 拷贝属性
            BeanUtils.copyProperties(dishDto, dish);
            //更新菜品表
            log.info("拷贝完成之后的菜品属性:{}", dish);
            dishMapper.updateById(dish);
            //获取口味数据集合
            List<DishFlavor> flavors = dishDto.getFlavors();
            log.info("口味数据集合:{}", flavors);
            /*更新口味表*/
            log.info("删除原先存在的口味...");
            // 1.根据菜品id删除原先存在的口味
            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
            dishFlavorService.remove(lambdaQueryWrapper);
            // 2.将菜品id重新到封装口味集合中，并清空key
            flavors = flavors.stream().peek((item) -> {
                item.setDishId(dishDto.getId());
                item.setId(null);
            }).collect(Collectors.toList());
            log.info("再次新增更新后的口味:{}", flavors);
            // 3.再次新增更新后的口味
            dishFlavorService.saveBatch(flavors);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int deleteDishByIds(List<Long> ids) {
        //判断菜品状态是否可以删除
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件:id,status=1
        dishLambdaQueryWrapper.in(ids != null, Dish::getId, ids);
        dishLambdaQueryWrapper.eq(Dish::getStatus, CommonEnum.Setmeal_DISH_STATUS_YES.getValue());
        Long count = dishMapper.selectCount(dishLambdaQueryWrapper);
        // 不能删除
        if (count > 0) {
            //抛出异常
            throw new BaseRuntimeException("你要删除的菜品正在售卖中，无法删除！");
        }
        //删除菜品
        return dishMapper.deleteBatchIds(ids);
    }

    @Override
    public int modifyDishByStatus(Integer status, Long[] ids) {
        Dish dish = null;
        int result = 0;
        for (Long id : ids) {
            dish = dishMapper.selectById(id);
            dish.setStatus(status);
            int i = dishMapper.updateById(dish);
            if (i > 0) {
                result++;
            }
        }
        return result;
    }

    @Override
    public List<Dish> queryDishListByCategoryId(Dish dish) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, dish.getCategoryId());
        return dishMapper.selectList(dishLambdaQueryWrapper);
    }


}




