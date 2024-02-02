package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.ShoppingCart;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title ShoppingCartMapper
 *@CreateTime 2024/1/31 23:14
 *@description
 **/
@Mapper
public interface ShoppingCartMapper extends BaseMapper<ShoppingCart> {
}
