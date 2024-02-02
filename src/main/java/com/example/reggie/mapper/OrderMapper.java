package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title OrderMapper
 *@CreateTime 2024/2/1 12:26
 *@description
 **/
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
