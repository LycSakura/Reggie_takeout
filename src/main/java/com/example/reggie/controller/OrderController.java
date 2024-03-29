package com.example.reggie.controller;

import com.example.reggie.common.R;
import com.example.reggie.entity.Orders;
import com.example.reggie.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 *@title OrderController
 *@CreateTime 2024/2/1 12:43
 *@description
 **/
@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * @param orders
     * @return R<String>
     * @description: 用户下单
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("订单数据: {}", orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }
}
