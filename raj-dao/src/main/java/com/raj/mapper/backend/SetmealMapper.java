package com.raj.mapper.backend;

import com.raj.entity.backend.Setmeal;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
* @author lenovo
* @description 针对表【t_setmeal(套餐)】的数据库操作Mapper
* @createDate 2022-11-14 16:43:15
* @Entity com.raj.entity.backend.Setmeal
*/
@Mapper
public interface SetmealMapper extends BaseMapper<Setmeal> {

}




