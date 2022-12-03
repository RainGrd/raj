package com.raj.mapper.backend;

import com.raj.entity.backend.DishFlavor;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 菜品口味关系表 Mapper 接口
 * </p>
 *
 * @author RainGrd
 * @since 2022-11-16
 */
@Mapper
public interface DishFlavorMapper extends BaseMapper<DishFlavor> {

}
