package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title CategoryMapper
 *@CreateTime 2024/1/29 14:46
 *@description
 **/
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
}
