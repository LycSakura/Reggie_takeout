package com.example.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.reggie.entity.Orders;

/***
 *@title OrderService
 *@CreateTime 2024/2/1 12:26
 *@description
 **/
public interface OrderService extends IService<Orders> {
    /**
     * @param orders
     * @description: 用户下单
     */
    public void submit(Orders orders);
}
