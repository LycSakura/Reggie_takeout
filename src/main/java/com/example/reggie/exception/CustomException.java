package com.example.reggie.exception;

/***
 *@title CustomException
 *@CreateTime 2024/1/29 16:14
 *@description 自定义业务异常
 **/
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
