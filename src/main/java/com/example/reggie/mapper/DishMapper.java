package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title DishMapper
 *@CreateTime 2024/1/29 15:45
 *@description
 **/
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
