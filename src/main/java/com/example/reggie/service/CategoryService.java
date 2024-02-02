package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.entity.Category;

/***
 *@title CategoryService
 *@CreateTime 2024/1/29 14:47
 *@description
 **/
public interface CategoryService extends IService<Category> {
    /**
     * @param id
     * @description: 根据id删除id，删除之前判断是否有关联菜品或者套餐
     */
    public void remove(Long id);
}
