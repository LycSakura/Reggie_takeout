package com.example.reggie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/***
 *@title ReggieApplication
 *@CreateTime 2024/1/28 12:38
 **/
@Slf4j
@SpringBootApplication
@ServletComponentScan // 扫描webFilter注解
@EnableTransactionManagement
@EnableCaching // 开启缓存注解功能
public class ReggieApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReggieApplication.class, args);
        log.info("项目启动成功 ... ");
    }
}
