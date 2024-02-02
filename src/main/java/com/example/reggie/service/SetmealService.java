package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.dto.SetmealDto;
import com.example.reggie.entity.Setmeal;

import java.util.List;

/***
 *@title SetmealService
 *@CreateTime 2024/1/29 15:47
 *@description
 **/
public interface SetmealService extends IService<Setmeal> {
    /**
     * @param setmealDto
     * @description: 新增套餐，同时需要保存套餐和菜品的关联关系
     */
    public void saveWithDish(SetmealDto setmealDto);
    /**
     * @param ids
     * @description: 删除套餐，同时删除套餐和菜品的关联数据
     */
    public void removeWithDish(List<Long> ids);
}
