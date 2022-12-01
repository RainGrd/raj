package com.raj.service.backend.impl;

import com.raj.entity.backend.DishFlavor;
import com.raj.mapper.backend.DishFlavorMapper;
import com.raj.service.backend.IDishFlavorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜品口味关系表 服务实现类
 * </p>
 *
 * @author RainGrd
 * @since 2022-11-16
 */
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements IDishFlavorService {

}
