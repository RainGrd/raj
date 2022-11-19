package com.raj.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.entity.backend.Category;
import com.raj.entity.backend.Setmeal;
import com.raj.entity.backend.SetmealDish;
import com.raj.backend.mapper.CategoryMapper;
import com.raj.backend.service.ISetmealDishService;
import com.raj.backend.service.SetmealService;
import com.raj.backend.mapper.SetmealMapper;
import com.raj.constants.CommonEnum;
import com.raj.dto.SetmealDto;
import com.raj.exception.BaseRuntimeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lenovo
 * @description 针对表【t_setmeal(套餐)】的数据库操作Service实现
 * @createDate 2022-11-14 16:43:15
 */
@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;
    @Autowired
    private ISetmealDishService setmealDishService;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Page<SetmealDto> querySetmealForPage(int pageSize, int page, String name) {
        //创建对象
        Page<SetmealDto> setmealDtoPage = new Page<>();
        Page<Setmeal> setmealPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Setmeal> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Setmeal::getName, name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Setmeal::getUpdateTime);
        //执行查询
        setmealMapper.selectPage(setmealPage, lambdaQueryWrapper);
        //拷贝属性
        BeanUtils.copyProperties(setmealDtoPage, setmealPage, "records");
        // 根据套餐id查询套餐菜品
        List<Setmeal> records = setmealPage.getRecords();
        //获取属性
        List<SetmealDto> collect = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            //拷贝属性
            BeanUtils.copyProperties(item, setmealDto);
            log.info("拷贝完成之后的的套餐数据传输对象:{}", setmealDto);
            //封装条件
            setmealDto.setCategoryName(categoryMapper.selectById(setmealDto.getCategoryId()).getName());
            return setmealDto;
        }).collect(Collectors.toList());
        //添加查询出来的套餐菜品集合
        setmealDtoPage.setRecords(collect);
        return setmealDtoPage;
    }

    @Override
    @Transactional
    public void saveSetmealBySetmealDto(SetmealDto setmealDto) {
        Setmeal setmeal = new Setmeal();
        //拷贝属性
        BeanUtils.copyProperties(setmealDto, setmeal);
        log.info("拷贝完成之后的套餐:{}", setmeal);
        //插入套餐
        int insert = setmealMapper.insert(setmeal);
        //获取setmealDto的菜品列表
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        log.info("setmealDto的菜品列表:{}", setmealDishes);
        //遍历菜品列表的steam流,并封装数据
        setmealDishes = setmealDishes.stream().peek((item) -> item.setSetmealId(setmeal.getId())).collect(Collectors.toList());
        //插入套餐的菜品列表
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public int deleteSetmealByIds(List<Long> ids) {
        //判断套餐状态是否可以删除
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //查询条件:id,status=1
        setmealLambdaQueryWrapper.in(ids != null, Setmeal::getId, ids);
        setmealLambdaQueryWrapper.eq(Setmeal::getStatus, CommonEnum.Setmeal_DISH_STATUS_YES.getValue());
        Long count = setmealMapper.selectCount(setmealLambdaQueryWrapper);
        // 不能删除
        if (count > 0) {
            //抛出异常
            throw new BaseRuntimeException("你要删除的套餐正在售卖中，无法删除！");
        }
        //批量删除套餐
        return setmealMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional
    public SetmealDto querySetmealById(Long id) {
        SetmealDto setmealDto = new SetmealDto();
        //根据套餐id查询套餐
        Setmeal setmeal = setmealMapper.selectById(id);
        log.info("拷贝前的套餐对象:{}", setmeal);
        //拷贝属性
        BeanUtils.copyProperties(setmeal, setmealDto);
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //根据套餐id查询该套餐下的菜品列表
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmealDto.getId());
        setmealDishLambdaQueryWrapper.eq(SetmealDish::getIsDeleted, CommonEnum.IS_DELETED_NO.getValue());
        List<SetmealDish> setmealDishes = setmealDishService.list(setmealDishLambdaQueryWrapper);
        log.info("该套餐下的菜品列表:{}", setmealDishes);
        //查询该套餐的分类
        Category category = categoryMapper.selectById(setmealDto.getCategoryId());
        //封装数据
        setmealDto.setSetmealDishes(setmealDishes);
        setmealDto.setCategoryName(category.getName());
        return setmealDto;
    }

    @Override
    @Transactional
    public void modifySetmealById(SetmealDto setmealDto) {
        //拷贝属性
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDto, setmeal);
        log.info("拷贝完成之后的套餐对象:{}", setmeal);
        //根据id修改套餐
        setmealMapper.updateById(setmeal);
        /*更新套餐菜品*/
        LambdaQueryWrapper<SetmealDish> setmealDishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //逻辑删除旧的套餐菜品
        setmealDishService.remove(setmealDishLambdaQueryWrapper.eq(SetmealDish::getSetmealId, setmeal.getId()));
        //新增套餐菜品
        //获取套餐菜品列表
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        //将套餐id封装获取的套餐菜品列表里面
        setmealDishes = setmealDishes.stream().peek((item) -> item.setSetmealId(setmeal.getId())).collect(Collectors.toList());
        //保存到套餐菜品表中
        setmealDishService.saveBatch(setmealDishes);
    }

    @Override
    public int modifySetmealByStatus(Integer status, Long[] ids) {
        int result = 0;
        for (Long id : ids) {
            //查询套餐
            Setmeal setmeal = setmealMapper.selectById(id);
            setmeal.setStatus(status);
            //更改套餐状态
            result = setmealMapper.updateById(setmeal);
        }
        return result;
    }
}




