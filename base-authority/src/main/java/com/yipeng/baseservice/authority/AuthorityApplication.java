package com.yipeng.baseservice.authority;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import tk.mybatis.spring.annotation.MapperScan;

/**
* @author yibingzhou
* email:zhouyibing_1990@163.com
* AUTO-GENERATE BY PROJECT-BUILDER
**/
@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = {"com.yipeng.framework","com.yipeng.baseservice.authority"})
@MapperScan(basePackages = {"com.yipeng.baseservice.authority.mapper"})
public class AuthorityApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthorityApplication.class, args);
    }
}