package com.raj.mapper.backend;

import com.raj.entity.backend.SetmealDish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 套餐菜品关系 Mapper 接口
 * </p>
 *
 * @author RainGrd
 * @since 2022-11-17
 */
@Mapper
public interface SetmealDishMapper extends BaseMapper<SetmealDish> {

}
