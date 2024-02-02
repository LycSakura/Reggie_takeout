package com.example.reggie.common;

/***
 *@title BaseContext
 *@CreateTime 2024/1/29 14:14
 *@description 基于ThreadLocal封装工具类，用户保存和读取当前登录用户的id
 **/
public class BaseContext {
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void setCurrentId(Long id) {
        threadLocal.set(id);
    }

    public static Long getCurrentId(){
        return threadLocal.get();
    }
}