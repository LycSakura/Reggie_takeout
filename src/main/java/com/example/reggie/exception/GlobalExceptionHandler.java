package com.example.reggie.exception;

import com.example.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

/***
 *@Description 全局异常处理
 *@title GlobalExceptionHandler
 *@CreateTime 2024/1/28 17:06
 **/
@ControllerAdvice(annotations = {RestController.class, Controller.class})
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {
    /**
     * @param ex
     * @return R<String>
     * @description: sql异常处理方法
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public R<String> exceptionHandler(SQLIntegrityConstraintViolationException ex) {
        log.error(ex.getMessage());
        // 出现唯一字段重复异常
        if (ex.getMessage().contains("Duplicate entry")) {
            String[] split = ex.getMessage().split(" ");
            String msg = split[2] + "已存在";
            return R.error(msg);
        }
        return R.error("未知错误");
    }
    /**
     * @param ex
     * @return R<String>
     * @description: 自定义的业务异常
     */
    @ExceptionHandler(CustomException.class)
    public R<String> CustomException(CustomException ex) {
        log.error(ex.getMessage());
        return R.error(ex.getMessage());
    }
}
