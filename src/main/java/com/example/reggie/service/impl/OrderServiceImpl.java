package com.example.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.reggie.common.BaseContext;
import com.example.reggie.entity.*;
import com.example.reggie.exception.CustomException;
import com.example.reggie.mapper.OrderMapper;
import com.example.reggie.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/***
 *@title OrderServiceImpl
 *@CreateTime 2024/2/1 12:27
 *@description
 **/
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Orders> implements OrderService {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private AddressBookService addressBookService;
    @Autowired
    private OrderDetailService orderDetailService;

    @Override
    @Transactional
    public void submit(Orders orders) {
        //获得当前用户id
        Long userId = BaseContext.getCurrentId();

        //查询当前用户的购物车数据
        LambdaQueryWrapper<ShoppingCart> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(ShoppingCart::getUserId, userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(lambdaQueryWrapper);

        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw new CustomException("购物车为空，不能下单");
        }

        //查询用户数据
        User user = userService.getById(userId);

        //查询地址数据
        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);

        if (addressBook == null) {
            throw new CustomException("地址信息有误，不能下单");
        }
        long orderId = IdWorker.getId(); //订单号

        AtomicInteger amount = new AtomicInteger(0);
        //设置订单明细表数据
        List<OrderDetail> orderDetail = shoppingCarts.stream().map(item -> {
            OrderDetail orderDetail1 = new OrderDetail();
            orderDetail1.setOrderId(orderId);
            orderDetail1.setNumber(item.getNumber());
            orderDetail1.setDishFlavor(item.getDishFlavor());
            orderDetail1.setDishId(item.getDishId());
            orderDetail1.setSetmealId(item.getSetmealId());
            orderDetail1.setName(item.getName());
            orderDetail1.setImage(item.getImage());
            orderDetail1.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail1;
        }).collect(Collectors.toList());


        //设置订单数据
        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(userId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getPhone());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + ((addressBook.getCityName() == null) ? "" : addressBook.getCityName())
                + ((addressBook.getDistrictName() == null) ? "" : addressBook.getDistrictName())
                + ((addressBook.getDetail() == null) ? "" : addressBook.getDetail()));
        //向订单表插入数据，一条数据
        this.save(orders);

        //向订单明细表插入数据，多条数据
        orderDetailService.saveBatch(orderDetail);

        //清空购物车数据
        shoppingCartService.remove(lambdaQueryWrapper);
    }
}
