package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title UserMapper
 *@CreateTime 2024/1/31 14:18
 *@description
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
