package com.example.reggie.config;

import javax.annotation.PostConstruct;

/***
 *@title DruidConfig
 *@CreateTime 2024/1/28 16:57
 **/
public class DruidConfig {
    /*
     * 解决druid 日志报错：discard long time none received connection:xxx
     * */
    @PostConstruct
    public void setProperties() {
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

}
