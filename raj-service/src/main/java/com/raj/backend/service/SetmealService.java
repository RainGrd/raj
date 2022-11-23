package com.raj.backend.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.raj.dto.SetmealDto;
import com.raj.entity.backend.Setmeal;

import java.util.List;

/**
 * @author lenovo
 * @description 针对表【t_setmeal(套餐)】的数据库操作Service
 * @createDate 2022-11-14 16:43:15
 */
public interface SetmealService {
    /**
     * 分页查询
     * @param pageSize
     * @param page
     * @param name
     * @return
     */
    Page<SetmealDto> querySetmealForPage(int pageSize, int page, String name);

    void saveSetmealBySetmealDto(SetmealDto setmealDto);

    int deleteSetmealByIds(List<Long> ids);

    SetmealDto querySetmealById(Long id);

    void modifySetmealById(SetmealDto setmealDto);

    int modifySetmealByStatus(Integer status, Long[] ids);

    List<Setmeal> querySetmealList(Setmeal setmeal);
}
