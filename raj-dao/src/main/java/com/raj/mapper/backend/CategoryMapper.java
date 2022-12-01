package com.raj.mapper.backend;

import com.raj.entity.backend.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
 * @author lenovo
 * @description 针对表【t_category(菜品及套餐分类)】的数据库操作Mapper
 * @createDate 2022-11-14 12:53:11
 * @Entity com.raj.entity.backend.Category
 */
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}




