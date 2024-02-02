package com.example.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/***
 *@title EmployeeMapper
 *@CreateTime 2024/1/28 13:41
 **/
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
