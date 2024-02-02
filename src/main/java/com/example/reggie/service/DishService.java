package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.dto.DishDto;
import com.example.reggie.entity.Dish;

/***
 *@title DishService
 *@CreateTime 2024/1/29 15:49
 *@description
 **/
public interface DishService extends IService<Dish> {
    /**
     * @param dishDto
     * @description: 新增菜品，同时插入菜品对应的口味数据，同时需要操作两张表:dish,dish_flavor
     */
    public void saveWithFlavor(DishDto dishDto);
    /**
     * @param id
     * @return DishDto
     * @description: 根据id查询菜品信息和对应的口味信息
     */
    public DishDto getByIdWithFlavor(Long id);
    /**
     * @param dishDto
     * @description: 更新菜品信息的同时更新口味信息
     */
    void updateWithFlavor(DishDto dishDto);
}
