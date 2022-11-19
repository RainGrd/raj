package com.raj.dto;

import com.raj.entity.backend.Dish;
import com.raj.entity.backend.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2017-2022, RainGrd
 * Author: lenovo
 * Date: 2022/11/16 12:38
 * FileName: DishDto
 * Description: 菜品Dto
 */
@Data
public class DishDto extends Dish {

    private static final long serialVersionUID = 8126312717610917655L;

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
